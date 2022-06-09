package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.annotation.Log;
import org.example.constant.Constants;
import org.example.core.RedisTemplate;
import org.example.dao.UserDao;
import org.example.entity.LoginUser;
import org.example.entity.Organ;
import org.example.entity.User;
import org.example.entity.UserExample;
import org.example.exception.PasswordIncorrectException;
import org.example.exception.UserNotFoundException;
import org.example.service.UserService;
import org.example.utils.RequestUtil;
import org.example.utils.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
@Service("UserService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public LoginUser login(String userName, String userPassword) {
        UserExample ex = new UserExample();
        ex.createCriteria().andUsernameEqualTo(userName);
        List<User> userList = userDao.selectByExample(ex);
        if(userList.isEmpty()){
            // 未找到用户
            throw new UserNotFoundException("[login]User not exist: " + userName);
        }
        else if(userList.size() > 1){
            // 找到多余一个用户（不允许出现！）
        }
        else{
            User user = userList.get(0);
            // 密码不正确
            if(!userPassword.equals(user.getUserPassword())){
                throw new PasswordIncorrectException("[login]Password incorrect!");
            }
        }

        User user = userList.get(0);
        // 更新时间戳
        Timestamp timestamp = TimeUtil.getTimeStamp();
        user.setLoginTime(timestamp);
        userDao.updateByPrimaryKey(user);
        // 生成token
        String token = UUID.randomUUID().toString();
        // 封装一个LoginUser
        LoginUser loginUser = LoginUser.builder()
                .token(token)
                .user(user)
                .build();
        // 把LoginUser保存到Redis中，key为 token:username:uuid，同时进行唯一登录的处理
        String keyPrefix = Constants.TOKEN_PREFIX + userName + ":";
//        String keyPrefix = Constants.TOKEN_PREFIX;
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        keys.forEach(key -> redisTemplate.remove(key));
        redisTemplate.setObject(keyPrefix+token, loginUser, Constants.TOKEN_TIME);

        return loginUser;
    }

    @Override
    public void logout() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取首部信息的token
        String token = RequestUtil.getToken();
        String username = RequestUtil.getUsername();
        System.out.println(token);
        // 删除redis中的token user这些数据
        redisTemplate.remove(Constants.TOKEN_PREFIX + username + ":"+ token);
    }

    @Override
    public LoginUser getUserInfo() {
        String username = RequestUtil.getUsername();
        UserExample userEx = new UserExample();
        userEx.createCriteria().andUsernameEqualTo(username);
        List<User> user = userDao.selectByExample(userEx);
        if(!user.isEmpty()){
            String token = RequestUtil.getToken();
            LoginUser loginUser = LoginUser.builder()
                    .token(token)
                    .user(user.get(0))
                    .build();
            return loginUser;
        }
        else{
            return null;
        }
    }

    @Override
    public Boolean register(User user) {
        String username = user.getUsername();
        UserExample ex = new UserExample();
        ex.createCriteria().andUsernameEqualTo(username);
        Long userCnt = userDao.countByExample(ex);
        if(userCnt > 0){ // 用户名已经存在
            return false;
        }
        else{ // 用户名不存在，可以注册
            userDao.insertSelective(user);
            return true;
        }
    }

    @Override
    @Log(title = "修改账户信息", logType = "其他")
    public Boolean editUserInfo(User user) {
        userDao.updateByPrimaryKeySelective(user);
        return true;
    }

    @Override
    public List<User> getAllUsers(String query) {
        List<User> records = null;
        UserExample userEx = new UserExample();
        if(query != null && !query.isEmpty()){
            userEx.or().andUsernameLike("%" + query + "%");
            userEx.or().andEmailLike("%" + query + "%");
            userEx.or().andPhoneLike("%" + query + "%");
        }
        else{
            userEx.createCriteria();
        }
        records = userDao.selectByExample(userEx);
        return records;
    }

    @Override
    @Log(title = "修改账户信息", logType = "其他")
    public Boolean editUser(Integer id, User user) {
        return editUserInfo(user);
    }

    @Override
    @Log(title = "删除账户信息", logType = "删除")
    public Boolean deleteUser(Integer id) {
        return userDao.deleteByPrimaryKey(id) > 0;
    }

}
