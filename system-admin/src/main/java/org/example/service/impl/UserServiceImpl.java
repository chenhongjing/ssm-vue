package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.core.RedisTemplate;
import org.example.dao.UserDao;
import org.example.entity.LoginUser;
import org.example.entity.User;
import org.example.entity.UserExample;
import org.example.exception.PasswordIncorrectException;
import org.example.exception.UserNotFoundException;
import org.example.service.UserService;
import org.example.utils.RequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

        // 生成token
        String token = UUID.randomUUID().toString();
        // 封装一个LoginUser
        LoginUser loginUser = LoginUser.builder()
                .token(token)
                .user(userList.get(0))
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
}
