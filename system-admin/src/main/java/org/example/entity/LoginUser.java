package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 已经登录的用户
 * @author: cangxiamy
 * @date: 2022.04.30
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    private String token;

    private User user;
}
