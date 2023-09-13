package com.example.core.member.service;

import com.example.core.member.domain.Store;
import com.example.core.member.dto.StoreDto;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface StoreService {
    public Store modifyStore(StoreDto storeDto);
    public Optional<Store> registerStore(String masterId , StoreDto storeDto);
    public void removeStore(String storeId);
    public Optional<Store> storeFindId(String id);
}
