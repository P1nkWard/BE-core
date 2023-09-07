package com.example.core.member.repository;

import com.example.core.member.domain.Store;
import com.example.core.member.dto.StoreDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,String> {

}
