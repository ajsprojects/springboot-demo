package com.example.springtest;

import com.example.springtest.Database.Users;
import com.example.springtest.Model.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersServiceTest {

    @Autowired
    UserService serviceMock;

    @Test
    public void getUserByIdSuccess() {

    }

    @Test
    public void getUserByIdFail() {

    }
}
