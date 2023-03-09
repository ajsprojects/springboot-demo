package com.example.springtest.repository;

import com.example.springtest.database.CustomerRepository;
import com.example.springtest.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findByEmailReturnsCustomer() {
        Optional<Customer> customer = customerRepository.findByEmail("email@email.com");
        assertTrue(customer.isPresent());
        assertThat(customer.get().getEmail()).isEqualTo("email@email.com");
    }
}
