package com.example.springtest.services;

import com.example.springtest.database.BookingRepository;
import com.example.springtest.model.Booking;
import com.example.springtest.model.Status;
import com.example.springtest.web.dto.requests.BookingRequest;
import com.example.springtest.web.dto.responses.BookingResponse;
import com.example.springtest.web.dto.responses.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;

    @Transactional
    public void bookHoliday(final BookingRequest bookingRequest, final Integer holidayId) {
        log.info("Booking holiday: " + bookingRequest);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        Booking newBooking = Booking.builder()
                .bookingReference(String.valueOf(bookingRequest.getCustomerRequest().hashCode() + holidayId))
                .customerId(bookingRequest.getCustomerRequest().getCustomerId()).holidayId(holidayId)
                .bookingDateTime(LocalDateTime.parse(LocalDateTime.now().format(format)))
                .startDate(bookingRequest.getStartDate())
                .endDate(bookingRequest.getEndDate())
                .bookingStatus(Status.ACTIVE).build();

        bookingRepository.save(newBooking);
    }

    public List<Booking> getBookingDetails(final int customerId) {
        return bookingRepository.findActiveBookingsForCustomer(customerId);
    }

    public ResponseEntity cancelBooking(final String bookingReference) {
        log.info("Cancelling booking reference" + bookingReference);
        Optional<Booking> booking = bookingRepository.findBookingByReference(bookingReference);
        if (!booking.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().errorMessage("Booking not found").build());
        }
        booking.get().setBookingStatus(Status.CANCELLED);
        bookingRepository.save(booking.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ErrorResponse.builder().errorMessage("Booking deleted").build());
    }

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookingEntities = bookingRepository.findAll();

        return bookingEntities.stream().filter(Objects::nonNull).map(booking -> BookingResponse.builder()
                .bookingReference(booking.getBookingReference())
                .bookingDateTime(booking.getBookingDateTime())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate()).bookingStatus(booking.getBookingStatus())
                .customerName("test").build())
                .collect(Collectors.toList());
    }
}

