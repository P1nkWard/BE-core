package com.example.core.member.exception;

/**
 * 이미 존재하는 회원이 있을 때 발생하는 예외
 */
public class MemberAlreadyExistsException extends RuntimeException {
    public MemberAlreadyExistsException(String message) {
        super(message);
    }
}