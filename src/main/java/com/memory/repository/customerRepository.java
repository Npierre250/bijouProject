package com.memory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.memory.model.Customer;

public interface customerRepository extends JpaRepository<Customer,Integer>{
    @Query("select b from Customer b where b.email=?1")
    public Customer findByEmail(String email);
}
