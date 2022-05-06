package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.MenuList;
import org.example.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
@RestController
@Slf4j
public class MenuController {
    @Resource
    private MenuService menuService;

    @RequestMapping("menu")
    public ResponseEntity<List<MenuList>> getMenu(){
        List<MenuList> menuLists = null;
        menuLists = menuService.getMenuList();
        if(menuLists == null){
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok().body(menuLists);
    }
}
