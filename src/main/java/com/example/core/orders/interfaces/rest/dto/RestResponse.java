package com.example.core.orders.interfaces.rest.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RestResponse<T> {
    private int status;
    private T data;

    private String message;
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
