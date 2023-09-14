package com.example.core.master.persistence;

import com.example.core.master.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master,String> {
    public void save();

}
