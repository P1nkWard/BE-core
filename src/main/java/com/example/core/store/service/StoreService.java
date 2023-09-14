package com.example.core.store.service;

import com.example.core.store.domain.Store;
import com.example.core.store.dto.StoreDto;

import java.util.Optional;

public interface StoreService {
    public Store modifyStore(StoreDto storeDto);
    public Optional<Store> registerStore(String masterId , StoreDto storeDto);
    public void removeStore(String storeId);
    public Optional<Store> storeFindId(String id);
}
