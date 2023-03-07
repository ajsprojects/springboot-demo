package com.example.springtest.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value
@Jacksonized
@Builder
public class CustomerDetailsResponse {

    Integer age;
    String name;
    String email;
    Address address;
    @JsonInclude(NON_EMPTY) // only include this response if it exists
    List<Booking> bookings;
}
