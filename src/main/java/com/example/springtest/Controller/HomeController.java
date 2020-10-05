package com.example.springtest.Controller;

import com.example.springtest.Model.HomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    HomeModel model;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        return model.helloName(name);
    }

    @GetMapping("/hello")
    public String helloEveryone() {
        return model.helloEveryone();
    }

}
