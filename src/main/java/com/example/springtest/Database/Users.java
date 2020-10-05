package com.example.springtest.Database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Users implements Serializable {
    @Id
    private int id;
    private int age;
    private String name;
    private String email;
    private String postcode;
}