package com.example.springtest.database;

import com.example.springtest.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Repository extends CrudRepository <User, Integer>{
    Optional <User> findByEmail(String email);
    List<User> findByAge(Integer age);
}
