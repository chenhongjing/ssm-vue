package org.example.service;

import org.example.entity.LoginUser;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
public interface UserService {

    LoginUser login(String userName, String userPassword);

    void logout();
}
