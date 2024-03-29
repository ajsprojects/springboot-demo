package com.example.springtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Integer id;
    @Column
    private Integer age;
    @Column
    private String name;
    @Column
    private String email;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}