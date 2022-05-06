package org.example.exception;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {
        super(s);
    }
}
