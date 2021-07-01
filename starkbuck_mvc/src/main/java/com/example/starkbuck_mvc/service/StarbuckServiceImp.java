package com.example.starkbuck_mvc.service;

import com.example.starkbuck_mvc.models.starbuck;
import com.example.starkbuck_mvc.repository.StarbuckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarbuckServiceImp implements StarbuckService {
    private StarbuckRepository starbuckRepository;

    @Autowired
    public StarbuckServiceImp(StarbuckRepository theStarbuckRepository) {
        starbuckRepository = theStarbuckRepository;
    }

    @Override
    public List<starbuck> findAll() {
        return starbuckRepository.findAll();
    }

    @Override
    public starbuck findById(int theId) {
        Optional<starbuck> result = starbuckRepository.findById(theId);
        starbuck starbuck = null;
        if(result.isPresent()) {
            starbuck = result.get();
        }else {
            throw  new RuntimeException("Not found for id:" + theId);
        }
        return starbuck;
    }

    @Override
    public void save(starbuck starbuck) {
        starbuckRepository.save(starbuck);
    }

    @Override
    public void deleteById(int theId) {
        starbuckRepository.deleteById(theId);
    }

}
