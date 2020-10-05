package com.example.springtest.Response;

import com.example.springtest.Database.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Response {
    public Users user;
    public HttpStatus status;
}
