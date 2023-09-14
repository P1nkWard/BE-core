package com.example.core.master.service;

import com.example.core.master.dto.MasterDto;
import com.example.core.master.entity.Master;
import com.example.core.master.exception.MasterAlreadyExistsException;
import com.example.core.master.exception.NotFoundMasterException;
import com.example.core.master.persistence.MasterRepository;
import com.example.core.member.service.ServiceCRUD;
import com.example.core.store.domain.Store;
import com.example.core.store.persistence.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class MasterServicePractice implements ServiceCRUD<MasterDto> {
    @Autowired
    MasterRepository masterRepository;
    StoreRepository storeRepository;

    @Override
    public MasterDto create(MasterDto masterDto) {
        Optional<Master> master = masterRepository.findById(masterDto.getMasterId());
        if (master.isPresent()) {
            throw new MasterAlreadyExistsException("이미 마스터 아이디가 존재합니다.");
        } else {
            Master newMaster = new Master(masterDto);
            masterRepository.save(newMaster);
            return masterDto;
        }
    }
    @Override
    public MasterDto modify(MasterDto masterDto) {
        Optional<Master> modifyMaster = masterRepository.findById(masterDto.getMasterId());
        if (modifyMaster.isPresent()) {
            Master updateMaster = new Master(masterDto);
            masterRepository.save(updateMaster);
            return masterDto;
        } else {
            throw new NotFoundMasterException("수정할 마스터가 없습니다.");
        }
    }
    public void remove(MasterDto masterDto) {
        Optional<Master> removeMaster = masterRepository.findById(masterDto.getMasterId());
        if(removeMaster.isPresent()){
            Set<Store> masterStore = removeMaster.get().getStoreSet();
            Iterator<Store> it = masterStore.iterator();
            while(it.hasNext()){
                // 둘의 연관관계를 끊어주는것
                Store store = it.next();
                store.setMaster(null);
            }
        }else{
            throw new NotFoundMasterException("삭제할 마스터가 없습니다.");
        }

    }
    public MasterDto find(MasterDto masterDto){
        Optional<Master> findMaster = masterRepository.findById(masterDto.getMasterId());
        if(findMaster.isPresent()){
            MasterDto resultMaster = new MasterDto(findMaster.get());
            return resultMaster;
        }else{
            throw new NotFoundMasterException("찾으시는 마스터가 없습니다.");
        }
    }
}