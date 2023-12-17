package com.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.model.Customer;
import com.memory.model.Payment;
import com.memory.repository.PaymentRepository;
@Service
public class PaymentServices {
    @Autowired private PaymentRepository repository;
    public Payment createPayment(Payment payment) {
        return repository.save(payment);
    }
    public Payment updatePayment(Payment payment) {
        return repository.save(payment);
    }
    public void deletePayment(Payment payment) {
          repository.deleteById(payment.getId());
    }
    public List<Payment>getPaymentList() {
        return repository.findAll();
    }
    public List<Payment> getCustomerPaymentList(Customer customer)
    {
        return repository.getCustomerPaymentList(customer);
    }
}
