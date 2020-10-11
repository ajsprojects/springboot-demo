package com.example.springtest.Services;

import com.example.springtest.Database.UserRepository;
import com.example.springtest.Model.NewUser;
import com.example.springtest.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User>  getUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public HttpStatus deleteUserById(int id)  {
        try {
            userRepository.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus addUser(String body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            NewUser newUser = objectMapper.readValue(body, NewUser.class);
            System.out.println("Inserting user into database: " + newUser.getName());
            userRepository.addUser(newUser.getName(), newUser.getEmail(), newUser.getPostcode(), newUser.getAge());
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
