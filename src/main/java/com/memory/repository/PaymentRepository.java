package com.memory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.memory.model.Customer;
import com.memory.model.Payment;

public interface PaymentRepository  extends JpaRepository<Payment,Integer>{
    @Query("select b from Payment  b where b.customerId=?1")
     public List<Payment> getCustomerPaymentList(Customer customer);
}
