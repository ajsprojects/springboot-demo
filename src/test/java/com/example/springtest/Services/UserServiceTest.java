package com.example.springtest.Services;

import com.example.springtest.Database.UserRepository;
import com.example.springtest.Model.User;
import com.example.springtest.TestData.CreateUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

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
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void init() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    void getUserById_Success() {
        User user  = new CreateUser().createNewUser();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Optional<User> response = userService.getUserById(14);
        verify(userRepository, times(1)).findById(anyInt());
        assertThat(response).isPresent();
        assertEquals(14, response.get().getId());
    }

    @Test
    void getUserById_Empty() {
        User user = new User();
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<User> response = userService.getUserById(14);
        verify(userRepository, times(1)).findById(anyInt());
        assertThat(response).isEmpty();
    }

    @Test
    void getUserByEmail_Success() {
        User user  = new CreateUser().createNewUser();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        Optional<User> response = userService.getUserByEmail("test@test.com");
        verify(userRepository, times(1)).findByEmail(anyString());
        assertThat(response).isPresent();
        assertEquals("test@test.com", response.get().getEmail());
    }

    @Test
    void getUserByEmail_Empty() {
        User user = new User();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Optional<User> response = userService.getUserByEmail("test@test.com");
        verify(userRepository, times(1)).findByEmail(anyString());
        assertThat(response).isEmpty();
    }

    @Test
    void getAllUsers() {
        User user = new CreateUser().createNewUser();
        ArrayList<User> userList  = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAllUsers()).thenReturn(userList);
        List<User> response = userService.getAllUsers();
        verify(userRepository, times(1)).findAllUsers();
        assertEquals(1, response.size());
        assertEquals(14, response.get(0).getId());
    }

    @Test
    void deleteUserById_Success() {
        doNothing().when(userRepository).deleteById(anyInt());
        HttpStatus status = userService.deleteUserById(anyInt());
        verify(userRepository, times(1)).deleteById(anyInt());
        assertEquals(HttpStatus.OK, status);
    }

    @Test
    void deleteUserById_Failure() {
        doThrow(new IllegalArgumentException()).when(userRepository).deleteById(anyInt());
        HttpStatus status = userService.deleteUserById(anyInt());
        verify(userRepository, times(1)).deleteById(anyInt());
        assertEquals(HttpStatus.NOT_FOUND, status);
    }

    @Test
    void addUser_Success() {
        String body = "{\n" +
                "\"age\": 30,\n" +
                "\"email\": \"polly@polly.com\",\n" +
                "\"name\": \"polly\",\n" +
                "\"postcode\": \"nr193dw\"\n" +
                "}";

        doNothing().when(userRepository).addUser(anyString(), anyString(), anyString(), anyInt());
        HttpStatus response = userService.addUser(body);
        verify(userRepository, times(1)).addUser(anyString(), anyString(), anyString(), anyInt());
        assertEquals(HttpStatus.OK, response);
    }

    @Test
    void addUser_Failure() {
        String body = "{\n" +
                "\"age\": 30,\n" +
                "\"email\": \"polly@polly.com\",\n" +
                "\"name\": \"polly\",\n" +
                "\"postcode\": \"nr193dw\"\n" +
                "}";

        doThrow(new IllegalArgumentException()).when(userRepository).addUser(anyString(), anyString(), anyString(), anyInt());
        HttpStatus response = userService.addUser(body);
        assertEquals(HttpStatus.BAD_REQUEST, response);
    }
}