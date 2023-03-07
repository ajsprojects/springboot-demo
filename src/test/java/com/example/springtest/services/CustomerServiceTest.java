package com.example.springtest.services;

import com.example.springtest.database.CustomerRepository;
import com.example.springtest.exception.UserException;
import com.example.springtest.model.Customer;
import com.example.springtest.model.Holiday;
import com.example.springtest.web.dto.responses.CustomerDetailsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static com.example.springtest.helper.Helper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookingService bookingService;
    @Mock
    private HolidayService holidayService;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void init() {
        assertThat(customerRepository).isNotNull();
    }

    @Test
    void getCustomerDetailsByIdReturnsMappedResponseWithBooking() {
        Customer customer = new Customer(1, 21, "timmy", "email@email.com", buildingAddressDatabaseEntity());
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
        when(bookingService.getBookingDetails(1)).thenReturn(Collections.singletonList(buildBookingDatabaseEntity()));
        when(holidayService.getHolidayDetails(10)).thenReturn(Optional.of(Holiday.builder().holidayName("holiday").build()));
        CustomerDetailsResponse actual = customerService.getCustomerDetailsById(1);
        assertEquals(buildCustomerDetailsResponseWithBooking(), actual);
    }

    @Test
    void getCustomerDetailsByIdThrowsExceptionWhenNotFound() {
        UserException exception = assertThrows(UserException.class, () -> customerService.getCustomerDetailsById(1));
        assertEquals("Exception finding user: 1", exception.getMessage());
    }

    @Test
    void getCustomerDetailsByEmailReturnsMappedResponseWithBooking() {
        Customer customer = new Customer(1, 21, "timmy", "email@email.com", buildingAddressDatabaseEntity());
        when(customerRepository.findByEmail("email@email.com")).thenReturn(Optional.of(customer));
        when(bookingService.getBookingDetails(1)).thenReturn(Collections.singletonList(buildBookingDatabaseEntity()));
        when(holidayService.getHolidayDetails(10)).thenReturn(Optional.of(Holiday.builder().holidayName("holiday").build()));
        CustomerDetailsResponse actual = customerService.getCustomerDetailsByEmail("email@email.com");
        assertEquals(buildCustomerDetailsResponseWithBooking(), actual);
    }

    @Test
    void getCustomerDetailsByIdReturnsMappedResponseNoBooking() {
        CustomerDetailsResponse expected = CustomerDetailsResponse.builder().age(21).email("email@email.com").name("timmy").address(buildAddress()).bookings(Collections.emptyList()).build();
        Customer customer = new Customer(1, 21, "timmy", "email@email.com", buildingAddressDatabaseEntity());
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
        when(bookingService.getBookingDetails(1)).thenReturn(Collections.emptyList());
        CustomerDetailsResponse actual = customerService.getCustomerDetailsById(1);
        assertEquals(expected, actual);
    }

}