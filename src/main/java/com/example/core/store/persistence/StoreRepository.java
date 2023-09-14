package com.example.core.store.persistence;

import com.example.core.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,String> {
    public void save();
    public void join();

}
