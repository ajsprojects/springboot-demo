package com.example.springtest.Services;

import com.example.springtest.Model.NewUser;
import com.example.springtest.Model.User;
import com.example.springtest.Database.UserRepository;
import com.example.springtest.Response.Response;
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

    public Optional<User>  getUserById(int id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public Optional<User> getUserByEmail(String email) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> user = userRepository.findAllUsers();
        return user;
    }

    public HttpStatus deleteUserById(int id) {
        try {
            userRepository.deleteById(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    public HttpStatus addUser(String body) throws Exception {
        Response response = new Response();
        ObjectMapper objectMapper = new ObjectMapper();
        NewUser newUser = objectMapper.readValue(body, NewUser.class);
        try {
            System.out.println("Inserting user into database: " + newUser.getName());
            userRepository.addUser(newUser.getName(), newUser.getEmail(), newUser.getPostcode(), newUser.getAge());
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
