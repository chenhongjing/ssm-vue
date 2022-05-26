package org.example.constant;

import java.io.File;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
public class Constants {
    // 统一的token前缀
    public static final String TOKEN_PREFIX = "token:";
    // token的过期时间
    public static final Long TOKEN_TIME = 2*30*60L;
    // 权限请求头
    public static final String HEAD_AUTHORIZATION = "Authorization";
    // 用户名请求头
    public static final String HEAD_USERNAME = "Username";
    // 分页连续显示的页数
    public static final int NAVIGATE_PAGES = 5;

    public static final String DOT = ".";

    public static final String SEPARATOR = "/";

    public static final String STREAM_CONTENT_TYPE = "application/octet-stream";

    public static final String ENCODING = "utf-8";
    // category 中分类1
    public static final Boolean HUMAN = Boolean.FALSE;
    // category 中分类1
    public static final Boolean ANIMAL = Boolean.TRUE;
    // role 中分类 1
    public static final Boolean ADMIN_USER = Boolean.TRUE;
    // role 中分类2
    public static final Boolean ORDINARY_USER = Boolean.FALSE;
}
