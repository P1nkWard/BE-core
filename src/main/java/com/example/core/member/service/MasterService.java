package com.example.core.member.service;

import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.MemberDto;
import org.springframework.stereotype.Service;

public interface MasterService {
    public void register(MasterDto masterDto);
    public void storeRegister();
    public void login(MasterDto masterDto);
    public void join(MasterDto masterDto);
    public MasterDto find(String id);
}
