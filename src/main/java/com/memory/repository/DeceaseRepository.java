package com.memory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memory.model.Decease;

public interface DeceaseRepository  extends JpaRepository<Decease,Integer>{
     
}
