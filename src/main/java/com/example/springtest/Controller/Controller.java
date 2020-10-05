package com.example.springtest.Controller;

import com.example.springtest.Response.Response;
import com.example.springtest.Database.Users;
import com.example.springtest.Model.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable(value = "id") int id) throws JsonProcessingException {
        System.out.println("Getting user by ID: " + id);
        Response response = userService.getUserById(id);
        if (response.getUser() != null) {
            return new ResponseEntity<>(response.getUser(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
}

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity getUserById(@PathVariable(value = "email") String email) {
        System.out.println("Getting user by ID: " + email);
        Response response = userService.getUserByEmail(email);
        if (response.getUser() != null) {
            return new ResponseEntity<>(response.getUser(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        System.out.println("Finding all users");
        return userService.getAllUsers();
    }

    @PostMapping("/deleteUserById/{id}")
    public Response deleteUserById(@PathVariable(value = "id") int id) {
        System.out.println("Deleting user with ID: " + id);
        return userService.deleteUserById(id);
    }
}
