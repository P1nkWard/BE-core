package com.example.core.member.exception;

/**
 * 찾으려고 하는 회원이 없을 때 발생하는 예외
 */
public class NotFoundMemberException extends RuntimeException{
    public NotFoundMemberException(String message){
        super(message);
    }
}
