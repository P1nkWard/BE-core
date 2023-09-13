package com.example.core.member.service;

import com.example.core.member.domain.Master;
import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.StoreDto;
import org.springframework.stereotype.Service;

public interface MasterService<T> {
    public void testG(T t);
    public void masterRegister(MasterDto masterDto);
    public Master findMaster(String id);
    public Master modifyMaster(MasterDto masterDto);
    // Master 도 T 로 받아오자 -> DTO 로 변환해서 받아오기
    public void removeMaster(String masterId);
}
