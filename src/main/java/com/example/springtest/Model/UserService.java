package com.example.springtest.Model;

import com.example.springtest.Database.UserRepository;
import com.example.springtest.Response.Response;
import com.example.springtest.Database.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Response getUserById(int id) throws JsonProcessingException {
        Optional<Users> users = userRepository.findById(id);
        Response response = new Response();
        if(!users.isEmpty()) {
            response.setUser(users.get());
            response.setStatus(HttpStatus.OK);
            return response;
        }
        response.setStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public Response getUserByEmail(String email) {
        Optional<Users> users = userRepository.findByEmail(email);
        Response response = new Response();
        if(!users.isEmpty()) {
            response.setUser(users.get());
            response.setStatus(HttpStatus.OK);
            return response;
        }
        response.setStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public List<Users> getAllUsers() {
        List<Users> users = userRepository.findAllUsers();
        Response response = new Response();
        if(!users.isEmpty()) {
            response.setStatus(HttpStatus.OK);
            return users;
        }
        response.setStatus(HttpStatus.NOT_FOUND);
        return (List<Users>) response;
    }

    public Response deleteUserById(int id) {
        Response response = new Response();
        try {
            userRepository.deleteById(id);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    public Response addUser(Users users) {
        return null;
    }
}
