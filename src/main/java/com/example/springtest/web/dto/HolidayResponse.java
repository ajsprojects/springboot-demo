package com.example.springtest.web.dto;

import com.example.springtest.model.Country;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Jacksonized
@Builder
public class HolidayResponse {
    private int id;
    private String holidayName;
    private String description;
    private int minimumAge;
    private BigDecimal price;
    private boolean flightsIncluded;
    private Country country;
    private String resortAddress;
    private String resortNotes;
}
