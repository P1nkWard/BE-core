package com.example.core.master.service;

import com.example.core.master.entity.Master;
import com.example.core.master.dto.MasterDto;

public interface MasterService {
//    public void testG(T t);
    public void masterRegister(MasterDto masterDto);
    public Master findMaster(String id);
    public Master modifyMaster(MasterDto masterDto);
    // Master 도 T 로 받아오자 -> DTO 로 변환해서 받아오기
    public void removeMaster(String masterId);
}
