package com.example.core.member.service;

import com.example.core.member.dto.StoreDto;

public class StoreDefaultService implements StoreService{

    public void join(StoreDto storeDto){

    }
    public StoreDto createStore(String masterId , String storeName){
        return new StoreDto(masterId,storeName);
    }
    public StoreDto updateStore(StoreDto storeDto){
        return storeDto;
    }
}
