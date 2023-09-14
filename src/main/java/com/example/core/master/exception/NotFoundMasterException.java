package com.example.core.master.exception;

/***
 * 찾으려고하는 마스터가 존재하지 않을
 */
public class NotFoundMasterException extends RuntimeException{
    public NotFoundMasterException(String message){
        super(message);
    }
}
