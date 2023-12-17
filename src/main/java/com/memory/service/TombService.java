package com.memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memory.model.Tomb;
import com.memory.repository.TombRepository;
@Service
public class TombService {
    @Autowired  private TombRepository repository;
    public Tomb createTomb(Tomb tomb)
    {
        return repository.save(tomb);
    }
    public Tomb updateTomb(Tomb tomb)
    {
        return repository.save(tomb);
    }
    public void deleteTomb(Tomb tomb)
    {
         repository.deleteById(tomb.getId());
    }
    public List<Tomb> getTombList()
    {
        return repository.findAll();
    }
    public Tomb findTombById(int id)
    {
        return repository.findById(id).get();
    }
}
