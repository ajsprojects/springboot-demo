package com.example.springtest.web.dto.responses;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class Address {
    String line1;
    String line2;
    String line3;
    String postcode;
    String country;
}
