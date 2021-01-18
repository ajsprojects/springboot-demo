package com.example.springtest.Database;

import com.example.springtest.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Integer>{
    Optional <User> findByEmail(String email);
    List<User> findByAge(Integer age);
}
