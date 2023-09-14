package com.example.core.member.exception;

/***
 * 찾으려고 하는 member 가 없을때
 */
public class NotFoundMemberException extends RuntimeException{
    public NotFoundMemberException(String message){
        super(message);
    }
}
