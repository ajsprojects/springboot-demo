package com.example.springtest.controller;

import com.example.springtest.model.User;
import com.example.springtest.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Users", description = "Users test API")
@RestController
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity getUserById(@PathVariable(value = "id") int id) {
        System.out.println("Getting user by ID: " + id);
        Optional<User> user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/age/{age}")
    @Operation(summary = "Get users by age")
    public ResponseEntity getUsersByAge(@PathVariable(value = "age") int age) {
        System.out.println("Getting user by ID: " + age);
        List<User> user = userService.getUsersByAge(age);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/email/{email:.*}")
    @Operation(summary = "Get user by email")
    public ResponseEntity getUserByEmail(@PathVariable(value = "email") String email) {
        System.out.println("Getting user by Email: " + email);
        Optional<User> user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete user by ID")
    public ResponseEntity deleteUserById(@PathVariable(value = "id") int id)  {
        System.out.println("Deleting user with ID: " + id);
        if(userService.deleteUserById(id)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }  else {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users")
    public ResponseEntity getAllUsers()  {
        System.out.println("Finding all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    @Operation(summary = "Add a new user")
    public ResponseEntity addUser(@RequestBody @Valid String request)  {
        System.out.println("Request: " + request);
        if(userService.addUser(request)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }
}
