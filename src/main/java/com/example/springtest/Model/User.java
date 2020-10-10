package com.example.springtest.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "users")
@Getter
@Setter
@Entity
public class User implements Serializable {
    @Id
    private int id;
    private int age;
    private String name;
    private String email;
    private String postcode;
}