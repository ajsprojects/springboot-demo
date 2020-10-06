package com.example.springtest.Controller;

import com.example.springtest.Response.Response;
import com.example.springtest.Response.ResponseUser;
import com.example.springtest.Model.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable(value = "id") int id) throws JsonProcessingException {
        System.out.println("Getting user by ID: " + id);
        ResponseUser responseUser = userService.getUserById(id);
        return new ResponseEntity<>(responseUser.getUser(), responseUser.getStatus());
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity getUserById(@PathVariable(value = "email") String email) {
        System.out.println("Getting user by Email: " + email);
        ResponseUser responseUser = userService.getUserByEmail(email);
        return new ResponseEntity<>(responseUser.getUser(), responseUser.getStatus());
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers() {
        System.out.println("Finding all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/deleteUserById/{id}")
    public ResponseEntity deleteUserById(@PathVariable(value = "id") int id) {
        System.out.println("Deleting user with ID: " + id);
        Response response = userService.deleteUserById(id);
        return new ResponseEntity<>(response.getBody(), response.getStatus());
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody String body) throws Exception {
        System.out.println("Adding user: " + body);
        Response response = userService.addUser(body);
        return new ResponseEntity<>(response.getBody(), response.getStatus());
    }
}
