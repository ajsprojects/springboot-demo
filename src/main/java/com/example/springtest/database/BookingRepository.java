package com.example.springtest.database;

import com.example.springtest.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT * FROM BOOKING b WHERE :customerId = b.customer_id AND b.booking_status = 'ACTIVE'", nativeQuery = true)
    List<Booking> findActiveBookingsForCustomer(@Param("customerId") final int customerId);

    @Query(value = "SELECT * FROM BOOKING b WHERE :bookingReference = b.booking_reference", nativeQuery = true)
    Optional<Booking> findBookingByReference(@Param("bookingReference") final String bookingReference);
}
