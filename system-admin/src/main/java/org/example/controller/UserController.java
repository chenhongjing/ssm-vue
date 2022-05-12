package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.LoginUser;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.12
 */
@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("user")
    public ResponseEntity<LoginUser> getUserInfo(){
        LoginUser loginUser = null;
        loginUser = userService.getUserInfo();
        if(loginUser == null){
            log.info("[error]cannot get login user info");
            return ResponseEntity.status(500).build();
        }
        log.info("get user info succeed!");
        return ResponseEntity.ok().body(loginUser);
    }
}
