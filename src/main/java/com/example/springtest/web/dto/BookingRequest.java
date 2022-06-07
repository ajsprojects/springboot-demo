package com.example.springtest.web.dto;

import com.example.springtest.model.Holiday;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import java.time.LocalDate;

@Value
@Builder
@Jacksonized
public class BookingRequest {

    @NonNull
    @Valid
    Customer customer;
    @NonNull
    @Valid
    Holiday holiday;
    @NonNull
    private LocalDate startDate;
    @NonNull
    private LocalDate endDate;
}
