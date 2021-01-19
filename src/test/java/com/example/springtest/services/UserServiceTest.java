package com.example.springtest.services;

import com.example.springtest.database.Repository;
import com.example.springtest.model.User;
import com.example.springtest.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private Repository repository;

    @InjectMocks
    private UserService userService;

    @Test
    void init() {
        assertThat(repository).isNotNull();
    }

    @Test
    void getUserById_Success() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(TestData.createMockUser()));
        Optional<User> response = userService.getUserById(14);
        verify(repository, times(1)).findById(anyInt());
        assertThat(response).isPresent();
        assertEquals(1, response.get().getId());
    }

    @Test
    void getUserById_Empty() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<User> response = userService.getUserById(1);
        verify(repository, times(1)).findById(anyInt());
        assertThat(response).isEmpty();
    }

    @Test
    void getUserByEmail_Success() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(TestData.createMockUser()));
        Optional<User> response = userService.getUserByEmail("test@test.com");
        verify(repository, times(1)).findByEmail(anyString());
        assertThat(response).isPresent();
        assertEquals("test@test.com", response.get().getEmail());
    }

    @Test
    void getUserByEmail_Empty() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        Optional<User> response = userService.getUserByEmail("test@test.com");
        verify(repository, times(1)).findByEmail(anyString());
        assertThat(response).isEmpty();
    }

    @Test
    void getAllUsers() {
        ArrayList<User> userList  = new ArrayList<>();
        userList.add(TestData.createMockUser());
        userList.add(TestData.createMockUser());
        when(repository.findAll()).thenReturn(userList);
        Iterable<User> response = userService.getAllUsers();
        verify(repository, times(1)).findAll();
        assertEquals(2, response.spliterator().getExactSizeIfKnown());
    }

    @Test
    void deleteUserById_Success() {
        doNothing().when(repository).deleteById(anyInt());
        boolean status = userService.deleteUserById(anyInt());
        verify(repository, times(1)).deleteById(anyInt());
        assertEquals(true, status);
    }

    @Test
    void deleteUserById_Failure() {
        doThrow(new IllegalArgumentException()).when(repository).deleteById(anyInt());
        boolean status = userService.deleteUserById(anyInt());
        verify(repository, times(1)).deleteById(anyInt());
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

        when(repository.save(any())).thenReturn(any());
        boolean response = userService.addUser(body);
        verify(repository, times(1)).save(any(User.class));
        assertEquals(true, response);
    }

    @Test
    void addUser_Failure() {
        String request = "{\n" +
                "\"age\": 9999,\n" +
                "\"email\": \"polly@polly.com\",\n" +
                "\"postcode\": \"nr193dw\"\n" +
                "}";

        doThrow(new IllegalArgumentException()).when(repository).save(any(User.class));
        boolean response = userService.addUser(request);
        assertEquals(false, response);
    }
}