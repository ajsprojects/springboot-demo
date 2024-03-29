package com.example.springtest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @Column(name = "booking_reference")
    private String bookingReference;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "holiday_id")
    private int holidayId;
    @Column(name = "booking_date_time")
    private LocalDateTime bookingDateTime;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private Status bookingStatus;
}
