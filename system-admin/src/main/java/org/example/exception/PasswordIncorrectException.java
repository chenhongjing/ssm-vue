package org.example.exception;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.04.30
 */
public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String s) {
        super(s);
    }
}
