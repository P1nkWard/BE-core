package com.example.core.member.persistence;

import com.example.core.member.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,String> {
    public void save();
    public void join();

}
