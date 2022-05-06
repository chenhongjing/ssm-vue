package org.example.service.impl;

import org.example.dao.MenuDao;
import org.example.entity.Menu;
import org.example.entity.MenuExample;
import org.example.entity.MenuList;
import org.example.service.MenuService;
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

    @Override
    public List<MenuList> getMenuList() {
        MenuExample levelOneExample = new MenuExample();
        // 获得一级菜单
        levelOneExample.createCriteria().andParentIdEqualTo(0);
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
