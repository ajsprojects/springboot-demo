package com.example.springtest.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Integer age;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String postcode;

    protected User() {
    }

    public User(Integer age, String name, String email, String postcode) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.postcode = postcode;
    }
}