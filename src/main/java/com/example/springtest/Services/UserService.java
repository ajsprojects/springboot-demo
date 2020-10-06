package com.example.springtest.Services;

import com.example.springtest.Database.NewUser;
import com.example.springtest.Database.UserRepository;
import com.example.springtest.Database.Users;
import com.example.springtest.Response.Response;
import com.example.springtest.Response.ResponseUser;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public ResponseUser getUserById(int id) throws JsonProcessingException {
        Optional<Users> users = userRepository.findById(id);
        ResponseUser responseUser = new ResponseUser();
        if(!users.isEmpty()) {
            responseUser.setUser(users.get());
            responseUser.setStatus(HttpStatus.OK);
            return responseUser;
        }
        responseUser.setStatus(HttpStatus.NOT_FOUND);
        return responseUser;
    }

    public ResponseUser getUserByEmail(String email) {
        Optional<Users> users = userRepository.findByEmail(email);
        ResponseUser responseUser = new ResponseUser();
        if(!users.isEmpty()) {
            responseUser.setUser(users.get());
            responseUser.setStatus(HttpStatus.OK);
            return responseUser;
        }
        responseUser.setStatus(HttpStatus.NOT_FOUND);
        return responseUser;
    }

    public List<Users> getAllUsers() {
        List<Users> users = userRepository.findAllUsers();
        ResponseUser responseUser = new ResponseUser();
        if(!users.isEmpty()) {
            responseUser.setStatus(HttpStatus.OK);
            return users;
        }
        responseUser.setStatus(HttpStatus.NOT_FOUND);
        return (List<Users>) responseUser;
    }

    public Response deleteUserById(int id) {
        Response response = new Response();
        try {
            userRepository.deleteById(id);
            response.setStatus(HttpStatus.OK);
            response.setBody("Success");
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setBody("Failure");
        }
        return response;
    }

    public Response addUser(String body) throws Exception {
        Response response = new Response();
        ObjectMapper objectMapper = new ObjectMapper();
        NewUser newUser = objectMapper.readValue(body, NewUser.class);

        try {
            System.out.println("Inserting user into database: " + newUser.getName());
            userRepository.addUser(newUser.getName(), newUser.getEmail(), newUser.getPostcode(), newUser.getAge());
            response.setStatus(HttpStatus.OK);
            response.setBody("Success");
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setBody("Failure");
        }
        return response;
    }
}
