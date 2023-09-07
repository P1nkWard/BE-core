package com.example.core.member.domain;

import com.example.core.member.dto.StoreDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    // 사장님 id 외래키
    // 음식 List
    @Id
    private String storeName;
    @Embedded
    private Phone storePhone;
    @Embedded
    private Address storeAddress;

    @ManyToOne
    @JoinColumn(name = "masterId")
    private Master master;

    public Store(StoreDto storeDto) {
        this.storeName = storeDto.getStoreName();
        this.storePhone = storeDto.getStorePhone();
        this.storeAddress = storeDto.getStoreAddress();
        this.master = storeDto.getMaster();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Phone getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(Phone storePhone) {
        this.storePhone = storePhone;
    }


    public Address getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(Address storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
}
