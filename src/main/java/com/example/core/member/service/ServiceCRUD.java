package com.example.core.member.service;

import java.util.Optional;

public interface ServiceCRUD<T>{
    // 연관관계를 갖고있는 CRUD는 DTO 자체로 받아오기로 약속함
    // 그래서 인터페이스화 해서 코드 리펙토링 시도
    public default T create(T t){

        return t;
    }
    public default T modify(T t){

        return t;
    }
    public default void remove(T t){

    }
    public default T find(T t){

        return t;
    }

}
