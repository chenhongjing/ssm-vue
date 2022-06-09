package org.example.service;

import org.example.entity.LoginUser;
import org.example.entity.User;

import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
public interface UserService {

    LoginUser login(String userName, String userPassword);

    void logout();

    LoginUser getUserInfo();

    Boolean register(User user);

    Boolean editUserInfo(User user);

    List<User> getAllUsers(String query);

    Boolean deleteUser(Integer id);

    Boolean editUser(Integer id, User user);
}
