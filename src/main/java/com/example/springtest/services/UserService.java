package com.example.springtest.services;

import com.example.springtest.database.CustomerRepository;
import com.example.springtest.exception.UserException;
import com.example.springtest.model.Customer;
import com.example.springtest.web.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private CustomerRepository customerRepository;

    public Customer getUserById(int id) {
        log.info("Getting user by ID: " + id);
        return customerRepository.findById(id).orElseThrow(() -> new UserException("Exception finding user: " + id));
    }

    public Customer getUserByEmail(String email) {
        log.info("Getting user by email: " + email);
        return customerRepository.findByEmail(email).orElseThrow(() -> new UserException("Exception finding user: " + email));
    }

    public Iterable<Customer> getAllUsers() {
        return customerRepository.findAll();
    }
}
