package com.example.core.member.service;

import com.example.core.member.domain.Master;
import com.example.core.member.domain.Store;
import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.StoreDto;
import com.example.core.member.repository.MasterRepository;
import com.example.core.member.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class MasterDefaultService implements MasterService {
    @Autowired
    MasterRepository masterRepository;
    @Autowired
    StoreRepository storeRepository;


    @Override
    public void masterRegister(MasterDto masterDto){
    Optional<Master> master = masterRepository.findById(masterDto.getMasterId());
        if(master.isPresent()){
            throw new NoSuchElementException("해당 ID가 이미 존재합니다.");
        }else {
            Master newMaster = new Master(masterDto);
            System.out.println("회원가입에 성공하셨습니다!");
            masterRepository.save(newMaster);
        }
    }
    @Override
    public  Master findMaster(String id){
        Optional<Master> findMaster = masterRepository.findById(id);
        if(findMaster.isPresent()){
            return findMaster.get();
        }else{
            throw new NoSuchElementException("아이디가 없습니다 다시 입력했는지 확인해주세요");
        }
    }
    @Override
    public Master modifyMaster(MasterDto masterDto){
        Master modifyMaster = new Master(masterDto);
        masterRepository.save(modifyMaster);
        return modifyMaster;
    }
    @Override
    public void removeMaster(String masterId){
        Optional<Master> removeMaster = masterRepository.findById(masterId);
        if(removeMaster.isPresent()){
            Set<Store> masterStore = removeMaster.get().getStoreSet();
            Iterator<Store> it = masterStore.iterator();
            while(it.hasNext()){
                Store store = it.next();
                store.setMaster(null);
            }
        }else{
            throw new NoSuchElementException("삭제할 계정이 없습니다.");
        }
    }
}
