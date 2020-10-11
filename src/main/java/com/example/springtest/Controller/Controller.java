package com.example.springtest.Controller;

import com.example.springtest.Model.User;
import com.example.springtest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable(value = "id") int id) throws Exception {
        System.out.println("Getting user by ID: " + id);
        Optional<User> user = userService.getUserById(id);
        if(!user.isEmpty()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getUserByEmail/{email:.*}")
    public ResponseEntity getUserByEmail(@PathVariable(value = "email") String email) throws Exception {
        System.out.println("Getting user by Email: " + email);
        Optional<User> user = userService.getUserByEmail(email);
        if(!user.isEmpty()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers()  throws Exception {
        System.out.println("Finding all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/deleteUserById/{id}")
    public ResponseEntity deleteUserById(@PathVariable(value = "id") int id)  throws Exception {
        System.out.println("Deleting user with ID: " + id);
        HttpStatus response = userService.deleteUserById(id);
        String responseBody = "";
        if(response.equals(HttpStatus.OK)) {
            responseBody = "Success";
        } else responseBody = "Failure";
        return new ResponseEntity<>(responseBody, response);
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody String body) throws Exception {
        System.out.println("Adding user: " + body);
        HttpStatus response = userService.addUser(body);
        String responseBody = "";
        if(response.equals(HttpStatus.OK)) {
            responseBody = "Success";
        } else responseBody = "Failure";
        return new ResponseEntity<>(responseBody, response);
    }
}
