package com.memory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memory.model.OtherServices;

public interface OtherServiceRepository  extends JpaRepository<OtherServices,Integer> {
    
}
