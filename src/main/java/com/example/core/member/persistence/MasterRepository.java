package com.example.core.member.persistence;

import com.example.core.member.domain.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master,String> {
    public void save();

}
