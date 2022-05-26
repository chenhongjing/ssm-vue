package org.example.service.impl;

import org.example.constant.Constants;
import org.example.dao.MenuDao;
import org.example.dao.UserDao;
import org.example.entity.*;
import org.example.exception.UserNotFoundException;
import org.example.service.MenuService;
import org.example.utils.RequestUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Resource
    private UserDao userDao;

    @Override
    public List<MenuList> getMenuList() {
        // 获得用户的权限
        String username = RequestUtil.getUsername();
        UserExample userEx = new UserExample();
        userEx.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userDao.selectByExample(userEx);
        if(userList.size() != 1){
            throw new UserNotFoundException("unique user not found!");
        }
        User user = userList.get(0);

        MenuExample levelOneExample = new MenuExample();
        // 获得一级菜单，注意用户的权限
        if(user.getRole() == Constants.ORDINARY_USER){
            levelOneExample.createCriteria().andParentIdEqualTo(0).andRoleEqualTo(Constants.ORDINARY_USER);
        }
        else{
            levelOneExample.createCriteria().andParentIdEqualTo(0);
        }
        List<Menu> levelOneMenus = menuDao.selectByExample(levelOneExample);
        // 获取一一对应的二级菜单，封装成完整的菜单列表
        List<MenuList> menuLists = new ArrayList<>();
        for(Menu item: levelOneMenus){
            MenuExample levelTwoExample = new MenuExample();
            levelTwoExample.createCriteria().andParentIdEqualTo(item.getMenuId());
            List<Menu> child = menuDao.selectByExample(levelTwoExample);
            MenuList levelOneItem = MenuList.builder().current(item).children(child).build();
            menuLists.add(levelOneItem);
        }
        return menuLists;
    }
}
