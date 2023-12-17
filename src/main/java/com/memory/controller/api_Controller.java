package com.memory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.memory.model.Customer;
import com.memory.model.Tomb;
import com.memory.service.TombService;
import com.memory.service.customerServices;
@RestController
public class api_Controller {
    @Autowired private TombService tombService;
    @Autowired private customerServices customerServices;
    @GetMapping(value = "/tomb/{id}")
    public ResponseEntity<Tomb> getTOmbById(@PathVariable int id) {
        Tomb tomb=tombService.findTombById(id);
        return ResponseEntity.ok(tomb);
    }
     @GetMapping(value = "/user/{id}")
    public Customer getUserById(@PathVariable int id) {
        return customerServices.findCustomerById(id);
    }
}
