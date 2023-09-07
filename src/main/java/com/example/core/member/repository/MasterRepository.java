package com.example.core.member.repository;

import com.example.core.member.domain.Master;
import com.example.core.member.dto.MasterDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master,String> {
    public void save();

}
