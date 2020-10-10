package com.example.springtest.TestData;

import com.example.springtest.Model.User;

public class CreateUser {

    public User createNewUser() {
        User user = new User();
        user.setId(14);
        user.setAge(14);
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setPostcode("pe914f");
        return user;
    }
}
