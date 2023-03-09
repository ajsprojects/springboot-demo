package com.example.springtest.helper;

import com.example.springtest.model.Booking;
import com.example.springtest.model.Country;
import com.example.springtest.model.Status;
import com.example.springtest.web.dto.responses.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Helper {

    private static final String dateTime = "2023-09-09 12:12:12";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static CustomerDetailsResponse buildCustomerDetailsResponse() {
        return CustomerDetailsResponse.builder()
                .age(21)
                .email("email@email.com")
                .name("timmy")
                .address(buildAddress()).build();
    }

    public static CustomerDetailsResponse buildCustomerDetailsResponseWithBooking() {
        return CustomerDetailsResponse.builder()
                .age(21)
                .email("email@email.com")
                .name("timmy")
                .address(buildAddress())
                .bookings(Collections.singletonList(com.example.springtest.web.dto.responses.Booking.builder()
                        .startDate("2023-09-09")
                        .endDate("2023-09-09")
                        .bookingReferenceId("reference")
                        .holidayName("holiday")
                        .build()))
                .build();
    }

    public static List<HolidayResponse> buildFullListOfHolidays() {
        return Arrays.asList(HolidayResponse.builder()
                        .id(1)
                        .holidayName("The young blast!")
                        .description("An amazing holiday")
                        .minimumAge(18).price(new BigDecimal(299.50))
                        .flightsIncluded(true)
                        .country(Country.ITALY)
                        .resortAddress(null)
                        .resortNotes(null)
                        .build(),

                HolidayResponse.builder()
                        .id(2)
                        .holidayName("The old blast!")
                        .description("An amazing holiday")
                        .minimumAge(55)
                        .price(new BigDecimal(599.99))
                        .flightsIncluded(true)
                        .country(Country.UNITED_KINGDOM)
                        .resortAddress(null)
                        .resortNotes(null).build(),

                HolidayResponse.builder()
                        .id(3)
                        .holidayName("The relaxing spanish holiday!")
                        .description("An amazing holiday")
                        .minimumAge(18)
                        .price(new BigDecimal(599.00))
                        .flightsIncluded(false)
                        .country(Country.SPAIN)
                        .resortAddress(null)
                        .resortNotes(null)
                        .build());
    }

    public static HolidayResponse buildHolidayResponse(final Country country) {
        return HolidayResponse.builder()
                .id(1)
                .holidayName("holiday name")
                .description("description")
                .minimumAge(18)
                .price(new BigDecimal("499.99"))
                .flightsIncluded(false)
                .country(country)
                .resortAddress("1 greece road")
                .resortNotes("")
                .build();
    }

    public static BookingResponse buildBookingResponse() {
        return BookingResponse.builder()
                .holidayName("the big one!")
                .bookingReference("referenceId")
                .bookingDateTime(LocalDateTime.parse(dateTime, formatter))
                .startDate(LocalDate.parse("2023-09-09"))
                .endDate(LocalDate.parse("2023-09-09"))
                .customerName("timmy").bookingStatus(Status.ACTIVE)
                .holidayDescription("A nice weekend away in greece")
                .customerEmail("timmy@timmy.com")
                .build();
    }

    public static ErrorResponse buildErrorResponse(final String message) {
        return ErrorResponse.builder().errorMessage(message).build();
    }

    public static Address buildAddress() {
        return Address.builder().line1("line1").line2("line2").line3("line3").country("UK").postcode("WA8L42").build();
    }

    public static com.example.springtest.model.Address buildingAddressDatabaseEntity() {
        return com.example.springtest.model.Address.builder().line1("line1").line2("line2").line3("line3").country("UK").postcode("WA8L42").addressId("1").build();
    }

    public static Booking buildBookingDatabaseEntity() {
        return Booking.builder()
                .customerId(1)
                .bookingStatus(Status.ACTIVE)
                .bookingReference("reference")
                .bookingDateTime(LocalDateTime.parse(dateTime, formatter))
                .startDate(LocalDate.parse("2023-09-09"))
                .endDate(LocalDate.parse("2023-09-09"))
                .holidayId(10)
                .build();
    }
}
