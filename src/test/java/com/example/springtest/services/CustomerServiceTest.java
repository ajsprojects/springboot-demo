package com.example.springtest.services;

import com.example.springtest.database.CustomerRepository;
import com.example.springtest.model.Customer;
import com.example.springtest.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void init() {
        assertThat(customerRepository).isNotNull();
    }

    @Test
    void getUserById_Success() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(TestData.createMockUser()));
        Optional<Customer> response = userService.getUserById(14);
        verify(customerRepository, times(1)).findById(anyInt());
        assertThat(response).isPresent();
        assertEquals(1, response.get().getId());
    }

    @Test
    void getUserById_Empty() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<Customer> response = userService.getUserById(1);
        verify(customerRepository, times(1)).findById(anyInt());
        assertThat(response).isEmpty();
    }

    @Test
    void getUserByEmail_Success() {
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(TestData.createMockUser()));
        Optional<Customer> response = userService.getUserByEmail("test@test.com");
        verify(customerRepository, times(1)).findByEmail(anyString());
        assertThat(response).isPresent();
        assertEquals("test@test.com", response.get().getEmail());
    }

    @Test
    void getUserByEmail_Empty() {
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Optional<Customer> response = userService.getUserByEmail("test@test.com");
        verify(customerRepository, times(1)).findByEmail(anyString());
        assertThat(response).isEmpty();
    }

    @Test
    void getAllUsers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(TestData.createMockUser());
        customerList.add(TestData.createMockUser());
        when(customerRepository.findAll()).thenReturn(customerList);
        Iterable<Customer> response = userService.getAllUsers();
        verify(customerRepository, times(1)).findAll();
        assertEquals(2, response.spliterator().getExactSizeIfKnown());
    }

    @Test
    void deleteUserById_Success() {
        doNothing().when(customerRepository).deleteById(anyInt());
        boolean status = userService.deleteUserById(anyInt());
        verify(customerRepository, times(1)).deleteById(anyInt());
        assertEquals(true, status);
    }

    @Test
    void deleteUserById_Failure() {
        doThrow(new IllegalArgumentException()).when(customerRepository).deleteById(anyInt());
        boolean status = userService.deleteUserById(anyInt());
        verify(customerRepository, times(1)).deleteById(anyInt());
        assertEquals(false, status);
    }

    @Test
    void addUser_Success() {
        String body = "{\n" +
                "\"age\": 30,\n" +
                "\"email\": \"test@test.com\",\n" +
                "\"name\": \"test\",\n" +
                "\"postcode\": \"nr193dw\"\n" +
                "}";

        when(customerRepository.save(any())).thenReturn(any());
        boolean response = userService.addUser(body);
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals(true, response);
    }

    @Test
    void addUser_Failure() {
        String request = "{\n" +
                "\"age\": 9999,\n" +
                "\"email\": \"polly@polly.com\",\n" +
                "\"postcode\": \"nr193dw\"\n" +
                "}";

        doThrow(new IllegalArgumentException()).when(customerRepository).save(any(Customer.class));
        boolean response = userService.addUser(request);
        assertEquals(false, response);
    }
}