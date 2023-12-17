package com.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.model.OtherServices;
import com.memory.repository.OtherServiceRepository;
@Service
public class otherServices {
    @Autowired
    private OtherServiceRepository repository;
    public OtherServices createOtherservice(OtherServices data) 
    {
        return repository.save(data);
    }
    public OtherServices updateOtherservice(OtherServices data) 
    {
        return repository.save(data);
    }
    public void deleteOtherservice(OtherServices data) 
    {
          repository.deleteById(data.getId());
    }
    public List<OtherServices> getAllOtherServiceslist()
    {
        return repository.findAll();
    }
}
