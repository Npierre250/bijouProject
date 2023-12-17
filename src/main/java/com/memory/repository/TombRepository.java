package com.memory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memory.model.Tomb;

public interface TombRepository  extends JpaRepository<Tomb,Integer>{
    
}
