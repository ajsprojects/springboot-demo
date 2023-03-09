package com.example.springtest.web.dto.requests;

import com.example.springtest.web.validator.CustomerDetailsMatch;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(required = true)
    CustomerRequest customerRequest;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(required = true)
    LocalDate startDate;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(required = true)
    LocalDate endDate;
}
