package com.example.starkbuck_mvc.service;

import com.example.starkbuck_mvc.models.starbuck;

import java.util.List;

public interface StarbuckService {
    public List<starbuck> findAll();

    public starbuck findById(int id);

    public void save(starbuck theStarbuck);

    public void deleteById(int theId);
}
