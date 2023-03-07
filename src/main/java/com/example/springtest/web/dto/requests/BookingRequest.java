package com.example.springtest.web.dto.requests;

import com.example.springtest.web.validator.CustomerDetailsMatch;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder
@Jacksonized
public class BookingRequest {
    @NonNull
    @CustomerDetailsMatch
    CustomerRequest customerRequest;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
}
