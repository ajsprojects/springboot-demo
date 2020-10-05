package com.example.springtest.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    private Long id;
    private int age;
    private String name;
    private String email;
    private String postcode;
}