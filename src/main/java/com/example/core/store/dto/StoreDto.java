package com.example.core.store.dto;

import com.example.core.member.domain.Address;
import com.example.core.master.entity.Master;
import com.example.core.member.domain.Phone;
import com.example.core.store.domain.Store;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    // 연관관계를 맺어줘야함 일단 이름만 해놓음
    private String storeName; // pk
    private Master master; // fk
    private Phone storePhone; // embedded
    private Address storeAddress; // embedded
    public StoreDto(Store store){
        this.storeName = store.getStoreName();
        this.master = store.getMaster();
        this.storePhone = store.getStorePhone();
        this.storeAddress = store.getStoreAddress();
    }

    public StoreDto(String storeName){
        this.storeName = storeName;
    }

    public Phone getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(Phone storePhone) {
        this.storePhone = storePhone;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Address getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(Address storeAddress) {
        this.storeAddress = storeAddress;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}