package com.example.springtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String addressId;
    @Column
    private String line1;
    @Column
    private String line2;
    @Column
    private String line3;
    @Column
    private String postcode;
    @Column
    private String country;
}
