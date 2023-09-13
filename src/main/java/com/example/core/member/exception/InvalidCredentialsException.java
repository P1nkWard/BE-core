package com.example.core.member.exception;

/**
 * ID가 존재하지 않거나 PW가 일치하지 않으면 발생하는 예외
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}