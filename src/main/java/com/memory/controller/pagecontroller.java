package com.memory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memory.model.Tomb;
import com.memory.repository.TombRepository;

public class pagecontroller {
    @RestController
public class ProductController {
    @Autowired
    private TombRepository tombRepository;
    @GetMapping("/products")
    public Page<Tomb> getProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return tombRepository.findAll(pageable);
    }
}

}
