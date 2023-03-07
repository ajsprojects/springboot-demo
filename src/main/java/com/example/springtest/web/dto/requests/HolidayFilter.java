package com.example.springtest.web.dto.requests;

import com.example.springtest.model.Country;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Jacksonized
@Builder
public class HolidayFilter {
    @JsonProperty(value = "age", required = true)
    @NonNull
    Integer customerAge;
    @NonNull
    @JsonProperty(value = "maximumPrice", required = true)
    BigDecimal priceLimit;
    @JsonProperty(value = "country", required = false)
    Country countryChoice;
}
