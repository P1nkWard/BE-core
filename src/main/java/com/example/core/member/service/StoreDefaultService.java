package com.example.core.member.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.example.core.member.domain.Master;
import com.example.core.member.domain.Store;
import com.example.core.member.dto.StoreDto;
import com.example.core.member.repository.MasterRepository;
import com.example.core.member.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StoreDefaultService implements StoreService {
    @Autowired
    MasterRepository masterRepository;
    @Autowired
    StoreRepository storeRepository;

    public Store modifyStore(StoreDto storeDto) {
        Optional<Store> store = storeRepository.findById(storeDto.getStoreName());
        if (store.isPresent()) {
            Store updateStore = new Store(storeDto);
            storeRepository.save(updateStore);
            return updateStore;
        } else {
            throw new NoSuchElementException("수정할 가게가 없습니다.");
        }
    }

    @Override
    public Optional<Store> registerStore(String masterId, StoreDto storeDto) {
        Optional<Master> master = masterRepository.findById(masterId);
        Optional<Store> store = storeRepository.findById(storeDto.getStoreName());
        if (store.isPresent()) {
            throw new NoSuchElementException("이미 존재하는 가게입니다.");
        } else {
            store.get().setMaster(master.get()); // store가 연관관계의 주인
            // 양방향 연관관계는 연관관계의 주인이 외래 키를 관리한다. 따라서 주인이 아닌 방향은
            // 값을 설정하지 않아도 데이터베이스에 외래 키 값이 정상 입력된다
            masterRepository.save(master.get());
            return store;
        }
    }

    public void removeStore(String storeId) {
        Optional<Store> removeStore = storeRepository.findById(storeId);
        if (removeStore.isPresent()) {
            Master removeMaster = removeStore.get().getMaster();
            if (removeMaster != null) {
                removeMaster.getStoreSet().remove(removeStore);
                removeStore.get().setMaster(removeMaster);
            }
        } else {
            throw new NoSuchElementException("삭제할 가게가 없습니다.");
        }
        storeRepository.delete(removeStore.get());
    }

    public void findStore(String storeId) {
        Optional<Store> findStore = storeRepository.findById(storeId);


    }
}
