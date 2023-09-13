package com.example.core.member.exception;

/**
 * 이미 존재하는 ID로 회원가입을 시도할 때 발생하는 예외
 */
public class MemberAlreadyExistsException extends RuntimeException {
    public MemberAlreadyExistsException(String message) {
        super(message);
    }
}