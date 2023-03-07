package com.example.springtest.web.dto.responses;

import com.example.springtest.model.Status;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Jacksonized
@Builder
public class BookingResponse {
    String bookingReference;
    String holidayName;
    String holidayDescription;
    String customerName;
    String customerEmail;
    LocalDateTime bookingDateTime;
    LocalDate startDate;
    LocalDate endDate;
    Status bookingStatus;
}
