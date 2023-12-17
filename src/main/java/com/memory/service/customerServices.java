package com.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.model.Customer;
import com.memory.repository.customerRepository;

@Service
public class customerServices {
    @Autowired private customerRepository customerRepository;
    public Customer createAccount(Customer customer) {
        return customerRepository.save(customer);}
        public Customer updateAccount(Customer customer) {
            return customerRepository.save(customer);} 
            public List<Customer> getCustomerList() {
                return customerRepository.findAll();}
                public void deleteAccount(Customer customer) {
                      customerRepository.deleteById(customer.getId());} 
                      public Customer findByEmail(String email) {
                        return customerRepository.findByEmail(email);}   
                        public Customer findCustomerById(int id) {
                            return customerRepository.findById(id).get();}            
}
