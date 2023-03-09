package com.example.springtest.integration;

import com.example.springtest.web.dto.responses.Booking;
import com.example.springtest.web.dto.responses.CustomerDetailsResponse;
import com.example.springtest.web.dto.responses.HolidayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;
import java.util.List;

import static com.example.springtest.helper.Helper.buildAddress;
import static com.example.springtest.helper.Helper.buildFullListOfHolidays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class IntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCustomerReturnsCustomerDetailsWithBookings() throws Exception {
        CustomerDetailsResponse expected =
                CustomerDetailsResponse.builder()
                        .age(24).name("Jane")
                        .email("jane@jane.com")
                        .address(buildAddress())
                        .bookings(Collections.singletonList(Booking.builder()
                                .holidayName("The relaxing spanish holiday!")
                                .startDate("2019-06-26").endDate("2019-06-26")
                                .bookingReferenceId("1721534").build()))
                        .build();

        webTestClient.get().uri("/customer/4")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(objectMapper.writeValueAsString(expected), true);
    }

    @Test
    public void testDeleteCustomerBookingReturnsCustomerDetailsWithNoBookings() throws Exception {
        CustomerDetailsResponse expected = CustomerDetailsResponse.builder()
                .age(24)
                .name("Jane")
                .email("jane@jane.com")
                .address(buildAddress())
                .build();

        webTestClient.patch().uri("/bookings/1721534")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isAccepted()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);

        webTestClient.get().uri("/customer/4")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(objectMapper.writeValueAsString(expected), true);
    }

    @Test
    public void testGetHolidaysReturnsListOfHolidays() throws Exception {
        List<HolidayResponse> expected = buildFullListOfHolidays();

        webTestClient.get().uri("/holidays/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk().expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(objectMapper.writeValueAsString(expected), true);
    }
}