package com.example.core.store.service;

import com.example.core.master.entity.Master;
import com.example.core.master.persistence.MasterRepository;
import com.example.core.member.service.ServiceCRUD;
import com.example.core.store.domain.Store;
import com.example.core.store.dto.StoreDto;
import com.example.core.store.exception.NotFoundStoreException;
import com.example.core.store.exception.StoreAlreadyExistsException;
import com.example.core.store.persistence.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class StoreServicePractice implements ServiceCRUD<StoreDto> {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    MasterRepository masterRepository;

    @Override
    public StoreDto create(StoreDto storeDto) {
        Optional<Store> getStore = storeRepository.findById(storeDto.getStoreName());
        Optional<Master> getMaster = masterRepository.findById(storeDto.getMaster().getMasterId());
        if(getStore.isPresent()){
            // exception
            throw new StoreAlreadyExistsException("가게가 이미 존재합니다.");
        }
        else{
            Store newStore = new Store(storeDto);
            newStore.setMaster(getMaster.get());
            // store가 연관관계의 주인
            // 양방향 연관관계는 연관관계의 주인이 외래 키를 관리한다. 따라서 주인이 아닌 방향은
            // 값을 설정하지 않아도 데이터베이스에 외래 키 값이 정상 입력된다
            // 객체까지 고려해서 주인이 아닌곳에도 값을 입력하자
            // 객체까지 고려해서 -> 말이 어려움 주석처리 추가
            storeRepository.save(newStore);
            return storeDto;
        }

    }
    @Override
    public StoreDto modify(StoreDto storeDto) {
        Optional<Store> getStore = storeRepository.findById(storeDto.getStoreName());
        if(getStore.isPresent()){
            Store updateStore = new Store(storeDto);
            storeRepository.save(updateStore);
            return storeDto;
        }
        else{
            throw new NotFoundStoreException("수정할 가게가 없습니다.");
        }
    }

    @Override
    public void remove(StoreDto storeDto) {
        Optional<Store> removeStore = storeRepository.findById(storeDto.getStoreName());
        if(removeStore.isPresent()){
            Master removeMaster = removeStore.get().getMaster();
            if(removeMaster!=null){
                removeMaster.getStoreSet().remove(removeStore);
                removeStore.get().setMaster(removeMaster);
            }
            else{
                throw new NotFoundStoreException("삭제할 가게가 없습니다.");
            }
            storeRepository.delete(removeStore.get());
        }

    }
    @Override
    public StoreDto find(StoreDto storeDto) {
        Optional<Store> findStore = storeRepository.findById(storeDto.getStoreName());
        if(findStore.isPresent()){
            StoreDto resultStore = new StoreDto(findStore.get());
            return resultStore;
        }else{
            throw new NotFoundStoreException("찾으시는 가게가 없습니다.");
        }
    }
}
