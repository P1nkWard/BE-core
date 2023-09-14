package com.example.core.store.exception;

public class StoreAlreadyExistsException extends RuntimeException{
    public StoreAlreadyExistsException(String message){
        super(message);
    }
}
