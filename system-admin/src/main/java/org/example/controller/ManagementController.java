package org.example.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.entity.LoginUser;
import org.example.entity.Organ;
import org.example.entity.SystemLog;
import org.example.entity.User;
import org.example.service.SystemLogService;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 系统资料管理
 * @author: cangxiamy
 * @date: 2022.06.07
 */
@RequestMapping("management")
@RestController
@Slf4j
public class ManagementController {
    @Resource
    private UserService userService;

    @Resource
    private SystemLogService systemLogService;

    @GetMapping("/user")
    public ResponseEntity<PageInfo> getUsers(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                             @RequestParam(value="query", required = false) String query){
        PageHelper.startPage(pageNum, pageSize);
        List<User> records = userService.getAllUsers(query);
        PageInfo page = new PageInfo(records, Constants.NAVIGATE_PAGES);
        log.info("get user records");
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PutMapping("user/{id}")
    public ResponseEntity<Boolean> editUser(@PathVariable Integer id, @RequestBody User user){
        if(user != null){
            if(userService.editUser(id, user)){
                log.info("update user succeed!");
                return ResponseEntity.ok().build();
            }
        }
        log.info("[error]user is null.");
        return ResponseEntity.status(500).build();
    }

    @GetMapping("/log")
    public ResponseEntity<PageInfo> getLogs(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                             @RequestParam(value="query", required = false) String query){
        PageHelper.startPage(pageNum, pageSize);
        List<SystemLog> records = systemLogService.getAllLogs(query);
        PageInfo page = new PageInfo(records, Constants.NAVIGATE_PAGES);
        log.info("get log records");
        return ResponseEntity.ok().body(page);
    }
}
