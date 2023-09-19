package com.example.core.master.dto;

import com.example.core.master.entity.Master;
import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.store.domain.Store;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class MasterDto{
    private String masterId; // pk
    private String masterPw;
    private String masterName;
    private String masterEmail;
    private Set<Store> store;
    private Phone phone; // embedded
    private Address address; // embedded

    public MasterDto(Master master){
        this.masterId = master.getMasterId();
        this.masterPw = master.getMasterPw();
        this.masterName = master.getMasterName();
        this.masterEmail = master.getMasterEmail();
        this.store = master.getStoreSet();
        this.phone = master.getMasterPhoneNumber();
        this.address = master.getMasterAddress();
    }
    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getMasterPw() {
        return masterPw;
    }

    public void setMasterPw(String masterPw) {
        this.masterPw = masterPw;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterEmail() {
        return masterEmail;
    }

    public void setMasterEmail(String masterEmail) {
        this.masterEmail = masterEmail;
    }

//    public Store getStore() {
//        return store;
//    }
//
//    public void setStore(Store store) {
//        this.store = store;
//    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
