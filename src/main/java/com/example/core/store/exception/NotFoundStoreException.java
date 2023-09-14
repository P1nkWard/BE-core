package com.example.core.store.exception;

import com.example.core.master.exception.NotFoundMasterException;

public class NotFoundStoreException extends RuntimeException{
    public NotFoundStoreException(String message){
        super(message);
    }
}
