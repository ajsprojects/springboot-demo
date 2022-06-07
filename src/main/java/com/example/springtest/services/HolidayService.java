package com.example.springtest.services;

import com.example.springtest.database.BookingRepository;
import com.example.springtest.database.HolidayRepository;
import com.example.springtest.model.Booking;
import com.example.springtest.model.Country;
import com.example.springtest.model.Holiday;
import com.example.springtest.web.dto.BookingRequest;
import com.example.springtest.web.dto.BookingResponse;
import com.example.springtest.web.dto.HolidayFilter;
import com.example.springtest.web.dto.HolidayResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class HolidayService {

    HolidayRepository holidayRepository;
    BookingRepository bookingRepository;

    public List<HolidayResponse> getAllHolidays(final Optional<Boolean> orderByPriceLowToHigh) {
        List<HolidayResponse> response = new ArrayList<>();

        List<Holiday> holidays = holidayRepository.findAll();

        holidays.stream()
                .filter(Objects::nonNull)
                .forEach(h -> response.add(buildHoliday(h)));

        if(orderByPriceLowToHigh.isPresent()) {
            response.sort(Comparator.comparing(HolidayResponse::getPrice));
        }
        return response;
    }
    
    public List<HolidayResponse> getFilteredHolidays(final HolidayFilter holidayFilter) {
        List<HolidayResponse> response = new ArrayList<>();

        List<Holiday> holidays = holidayRepository.findHolidaysWithFilter(holidayFilter.getCustomerAge(), holidayFilter.getPriceLimit());

        holidays.stream()
                .filter(Objects::nonNull)
                .filter(h -> {
                    Country country = holidayFilter.getCountryChoice();
                    if(Optional.ofNullable(country).isPresent()) {
                        return holidayFilter.getCountryChoice().equals(h.getCountry()) ? true : false;
                    }
                    return true;
                })
                .forEach(h -> response.add(buildHoliday(h)));
        return response;
    }

    private HolidayResponse buildHoliday(Holiday holiday) {
        return HolidayResponse.builder()
                .holidayName(holiday.getHolidayName())
                .description(holiday.getDescription())
                .flightsIncluded(holiday.isFlightsIncluded())
                .minimumAge(holiday.getMinimumAge())
                .country(holiday.getCountry())
                .price(new BigDecimal(String.valueOf(holiday.getPrice())))
                .id(holiday.getId())
                .build();
    }

    @Transactional
    public void bookHoliday(final BookingRequest bookingRequest) {
        Booking newBooking = Booking.builder()
                .bookingDateTime(LocalDateTime.now())
                .bookingReference(String.valueOf(bookingRequest.getCustomer().hashCode() + bookingRequest.hashCode()))
                .startDate(bookingRequest.getStartDate())
                .endDate(bookingRequest.getEndDate())
                .holidayId(bookingRequest.getHoliday().getId())
                .build();
        bookingRepository.save(newBooking);
    }

    public List<BookingResponse> getAllBookings() {
        List<BookingResponse> response = new ArrayList<>();

        List<Booking> bookings = bookingRepository.findAll();
        response.add(BookingResponse.builder().bookingStatus(bookings.get(0).getBookingStatus()).holiday(Holiday.builder().flightsIncluded(true).build()).build());

        return response;
    }
}

