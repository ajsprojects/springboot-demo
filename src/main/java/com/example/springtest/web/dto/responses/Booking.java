package com.example.springtest.web.dto.responses;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class Booking {
    String startDate;
    String endDate;
    String bookingReferenceId;
    String holidayName;
}
