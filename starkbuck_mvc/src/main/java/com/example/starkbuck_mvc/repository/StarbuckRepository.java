package com.example.starkbuck_mvc.repository;

import com.example.starkbuck_mvc.models.starbuck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StarbuckRepository extends JpaRepository<starbuck, Integer> {
}
