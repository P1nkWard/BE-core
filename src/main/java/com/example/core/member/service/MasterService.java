package com.example.core.member.service;

import com.example.core.member.domain.Master;
import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.StoreDto;
import org.springframework.stereotype.Service;

public interface MasterService {
    public void masterRegister(MasterDto masterDto);
    public Master findMaster(String id);
    public Master modifyMaster(MasterDto masterDto);
    public void removeMaster(String masterId);
}
