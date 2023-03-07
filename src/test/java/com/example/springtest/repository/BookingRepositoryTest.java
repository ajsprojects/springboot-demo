package com.example.springtest.repository;

import com.example.springtest.database.BookingRepository;
import com.example.springtest.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    BookingRepository bookingRepository;
    @Test
    void findActiveBookingForCustomerReturnsValidBooking() {
        List<Booking> bookingList = bookingRepository.findActiveBookingsForCustomer(1);
        assertThat(bookingList.size()).isEqualTo(1);
        assertThat(bookingList.get(0).getCustomerId()).isEqualTo(1);
    }

    @Test
    void findActiveForCustomerReturnsNoBookingWhenNoneExist() {
        List<Booking> bookingList = bookingRepository.findActiveBookingsForCustomer(111);
        assertTrue(bookingList.isEmpty());
    }

    @Test
    void findBookingByReferenceForCustomerReturnsValidBooking() {
        Optional<Booking> booking = bookingRepository.findBookingByReference("4534534");
        assertThat(booking.isPresent());
        assertThat(booking.get().getBookingReference()).isEqualTo("4534534");
        assertThat(booking.get().getCustomerId()).isEqualTo(1);
    }

    @Test
    void findBookingByReferenceForCustomerReturnsNoBookingWhenNoneExist() {
        Optional<Booking> booking = bookingRepository.findBookingByReference("22222");
        assertTrue(booking.isEmpty());
    }
}
