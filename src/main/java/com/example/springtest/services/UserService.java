package com.example.springtest.services;

import com.example.springtest.database.Repository;
import com.example.springtest.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Repository repository;

    @Autowired
    public UserService(Repository repository) {
        this.repository = repository;
    }

    public Optional<User> getUserById(int id) {
        return repository.findById(Integer.valueOf(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> getUsersByAge(Integer age) {
        return repository.findByAge(age);
    }

    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    public boolean deleteUserById(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addUser(String request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(request, User.class);
            System.out.println("Inserting user into database: " + user.toString());
            repository.save(new User(user.getAge(), user.getName(), user.getEmail(), user.getPostcode()));
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
