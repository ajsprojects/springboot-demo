package com.example.springtest.web.dto;

import com.example.springtest.model.Holiday;
import com.example.springtest.model.Status;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Jacksonized
@Builder
public class BookingResponse {
    private String bookingReference;
    private Holiday holiday;
    private LocalDateTime bookingDateTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status bookingStatus;
}
