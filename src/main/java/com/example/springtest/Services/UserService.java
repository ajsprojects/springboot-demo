package com.example.springtest.Services;

import com.example.springtest.Database.UserRepository;
import com.example.springtest.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(Integer.valueOf(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByAge(Integer age) {
        return userRepository.findByAge(age);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
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
            userRepository.save(new User(user.getAge(), user.getName(), user.getEmail(), user.getPostcode()));
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
