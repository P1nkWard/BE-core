package com.example.core.member.dto;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.domain.Store;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MasterDto{
    private String masterId; // pk
    private String masterPw;
    private String masterName;
    private String masterEmail;
    private Store store;
    private Phone phone; // embedded
    private Address address; // embedded

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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

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
