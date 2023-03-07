package com.example.springtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String holidayName;
    @Column
    private String description;
    @Column
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(name = "minimum_age")
    private int minimumAge;
    @Column
    private Double price;
    @Column(name = "flights_included")
    @Type(type = "true_false")
    private boolean flightsIncluded;
    @Column
    private int rating;
}
