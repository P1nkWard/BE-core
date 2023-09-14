package com.example.core.master.exception;

/***
 * 이미 존재하는 마스터 id 로 회원가입 시도
 */
public class MasterAlreadyExistsException extends RuntimeException{
    public MasterAlreadyExistsException(String message) {
        super(message);
    }
}
