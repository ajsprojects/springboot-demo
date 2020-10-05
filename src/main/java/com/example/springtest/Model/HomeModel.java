package com.example.springtest.Model;

import org.springframework.stereotype.Service;

@Service
public class HomeModel {

    public static String helloName(String name) {
        return String.format("Hello %s!", name);
    }

    public static String helloEveryone() {
        return String.format("Hello everyone!");
    }
}
