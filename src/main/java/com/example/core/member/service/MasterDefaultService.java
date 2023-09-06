package com.example.core.member.service;

import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterDefaultService implements MasterService {
    @Autowired
    MasterRepository masterRepository;
    @Override
    public void register(MasterDto masterDto){

    }

    @Override
    public void storeRegister() {

    }

    @Override
    public void login(MasterDto masterDto) {

    }
    @Override
    public void join(MasterDto masterDto){
        String id = masterDto.getId();
        List<MemberDto> memberList = new ArrayList<MemberDto>();
        for (int i = 0; i < memberList.size(); i++) {
                if(memberList.get(i).getId()==id){

                }
                else{

                }
        }

    }
    @Override
    public  MasterDto find(String id){
        List<MemberDto> memberList = new ArrayList<MemberDto>();
        for (int i = 0; i < memberList.size(); i++) {
            if(memberList.get(i).getId()==id){

            }
            else{
                return
            }
        }

    }
}
