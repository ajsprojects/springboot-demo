package com.example.springtest.web;

import com.example.springtest.services.BookingService;
import com.example.springtest.services.HolidayService;
import com.example.springtest.web.controllers.BookingsController;
import com.example.springtest.web.dto.requests.BookingRequest;
import com.example.springtest.web.dto.responses.BookingResponse;
import com.example.springtest.web.dto.requests.CustomerRequest;
import com.example.springtest.web.dto.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.springtest.helper.Helper.buildAddress;
import static com.example.springtest.helper.Helper.buildBookingResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookingsController.class)
public class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookingService bookingService;
    @MockBean
    private HolidayService holidayService;
    @Autowired
    private ObjectMapper objectMapper;

    public void contextLoads() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void bookAHolidayReturnsCreated() throws Exception {
        BookingRequest bookingRequest = BookingRequest.builder().startDate(LocalDate.parse("2023-09-09")).endDate(LocalDate.parse("2023-09-09")).customerRequest(CustomerRequest.builder().customerId(1).email("email@email.com").age(29).name("timmy").address(buildAddress()).build()).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/bookings/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void cancelBookingReturnsCancelledResponse() throws Exception {
        ResponseEntity expected = ResponseEntity.status(HttpStatus.ACCEPTED).body(ErrorResponse.builder().errorMessage("Booking deleted").build());
        when(bookingService.cancelBooking("3423423")).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.patch("/bookings/3423423")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected.getBody())));
    }

    @Test
    public void cancelBookingWhenBookingDoesNotExistReturnsBookingNotFound() throws Exception {
        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().errorMessage("Booking not found").build());

        when(bookingService.cancelBooking("3423423")).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.patch("/bookings/3423423")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected.getBody())));
    }

    @Test
    public void getAllBookingsReturnsBookings() throws Exception {
        List<BookingResponse> expected = new ArrayList<>();
        expected.add(buildBookingResponse());
        expected.add(buildBookingResponse());

        when(bookingService.getAllBookings()).thenReturn(expected);

        mockMvc.perform(get("/bookings/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
}

