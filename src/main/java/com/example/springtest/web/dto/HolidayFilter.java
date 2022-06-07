package com.example.springtest.web.dto;

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

    @JsonProperty("age")
    @NonNull
    private Integer customerAge;
    @NonNull
    @JsonProperty("maximumPrice")
    private BigDecimal priceLimit;
    @JsonProperty(value = "country", required = false)
    private Country countryChoice;
}
