package com.example.core.master.entity;

import com.example.core.member.domain.vo.Address;
import com.example.core.member.domain.vo.Phone;
import com.example.core.store.domain.Store;
import com.example.core.master.dto.MasterDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Master {
    @Id
    private String masterId;
    private String masterPw;
    private String masterName;
    private String masterEmail;
    @Embedded
    private Phone masterPhoneNumber;
    @Embedded
    private Address masterAddress;

    @OneToMany(mappedBy = "master")
    Set<Store> storeSet = new HashSet<Store>();

    public void addStore (Store store){
        this.storeSet.add(store);
        if(store.getMaster() != this){ // 무한루프에 빠지지않게 체크
            store.setMaster(this);
        }
    }

    public Master(MasterDto masterDto){
        this.masterId = masterDto.getMasterId();
        this.masterPw = masterDto.getMasterPw();
        this.masterName = masterDto.getMasterName();
        this.masterEmail = masterDto.getMasterEmail();
        this.masterPhoneNumber = masterDto.getPhone();
        this.masterAddress = masterDto.getAddress();
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

    public Phone getMasterPhoneNumber() {
        return masterPhoneNumber;
    }

    public void setMasterPhoneNumber(Phone masterPhoneNumber) {
        this.masterPhoneNumber = masterPhoneNumber;
    }

    public Set<Store> getStoreSet() {
        return storeSet;
    }

    public void setStoreSet(Set<Store> storeSet) {
        this.storeSet = storeSet;
    }

    public Address getMasterAddress() {
        return masterAddress;
    }

    public void setMasterAddress(Address masterAddress) {
        this.masterAddress = masterAddress;
    }
}
