package com.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.model.Decease;
import com.memory.repository.DeceaseRepository;
@Service
public class DeceaseService {
    @Autowired
    private DeceaseRepository repository;
 
    public Decease createDecease(Decease data)
    {
        return repository.save(data);
    }
    public Decease updateDecease(Decease data)
    {
        return repository.save(data);
    }
    public void deleteDecease(Decease data)
    {
         repository.deleteById(data.getId());
    }
    public List<Decease> getDeceaseList()
    {
        return repository.findAll();
    }
}
