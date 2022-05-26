package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.LoginUser;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 处理登录登出的Controller
 * @author: cangxiamy
 * @date: 2022.04.28
 */
@RestController
@Slf4j
public class IndexController {

    @Resource
    private UserService userService;

    @RequestMapping("login")
    public ResponseEntity<LoginUser> login(@RequestBody User user){
        LoginUser loginUser = null;
        loginUser = userService.login(user.getUsername(), user.getUserPassword());
        if(loginUser == null){
            return ResponseEntity.status(500).build();
        }
        log.info("login succeed!");
        return ResponseEntity.ok().body(loginUser);
    }

    @RequestMapping("logout")
    public ResponseEntity<String> logout(){
        userService.logout();
        return ResponseEntity.ok().body("退出成功");
    }

    @RequestMapping("register")
    public ResponseEntity<String> register(@RequestBody User user){
        if(userService.register(user)){
            log.info("register succeed!");
            return ResponseEntity.ok().body("注册成功");
        }
        else{
            log.info("register fail!");
            return ResponseEntity.status(500).body("注册失败！用户名已被注册！");
        }
    }
}
