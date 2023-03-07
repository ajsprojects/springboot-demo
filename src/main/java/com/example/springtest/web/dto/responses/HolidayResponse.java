package com.example.springtest.web.dto.responses;

import com.example.springtest.model.Country;
import com.example.springtest.web.dto.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Jacksonized
@Builder
public class HolidayResponse {
    int id;
    String holidayName;
    String description;
    int minimumAge;
    @JsonSerialize(using = MoneySerializer.class)
    BigDecimal price;
    boolean flightsIncluded;
    Country country;
    String resortAddress;
    String resortNotes;
}
