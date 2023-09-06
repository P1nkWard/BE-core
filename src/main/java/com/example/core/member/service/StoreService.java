package com.example.core.member.service;

import com.example.core.member.dto.StoreDto;

public interface StoreService {
    public void join(StoreDto storeDto);

    public StoreDto createStore(String masterId , String storeName);
    public StoreDto updateStore(StoreDto storeDto);

}
