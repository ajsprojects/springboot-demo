package com.example.springtest.testdata;

import com.example.springtest.model.User;

public class TestData {

    public static User createMockUser() {
        User user = new User(18, "test", "test@test.com", "le1084j");
        user.setId(1);
        return user;
    }
}
