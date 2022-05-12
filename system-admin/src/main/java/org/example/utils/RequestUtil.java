package org.example.utils;

import org.example.constant.Constants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.12
 */
public class RequestUtil {
    static public String getUsername(){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader(Constants.HEAD_USERNAME);
    }

    static public String getToken(){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader(Constants.HEAD_AUTHORIZATION);
    }
}
