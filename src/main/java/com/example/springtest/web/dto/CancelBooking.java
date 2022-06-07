package com.example.springtest.web.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;

@Value
@Jacksonized
@Builder
public class CancelBooking {

    @NonNull
    private int holidayId;
    @NotEmpty
    private String customerEmailAddress;
}
