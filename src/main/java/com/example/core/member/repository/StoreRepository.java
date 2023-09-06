package com.example.core.member.repository;

import com.example.core.member.dto.StoreDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreDto,String> {
    public void save();
    public void join();
}
