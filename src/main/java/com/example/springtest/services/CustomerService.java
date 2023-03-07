package com.example.springtest.services;

import com.example.springtest.database.CustomerRepository;
import com.example.springtest.exception.UserException;
import com.example.springtest.model.Booking;
import com.example.springtest.model.Customer;
import com.example.springtest.web.dto.responses.Address;
import com.example.springtest.web.dto.responses.CustomerDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private BookingService bookingService;
    private HolidayService holidayService;

    public CustomerDetailsResponse getCustomerDetailsById(final int id) {
        log.info("Getting user by ID: " + id);
        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new UserException("Exception finding user: " + id));
        return mapToCustomerDetailsResponse(customer);
    }

    public CustomerDetailsResponse getCustomerDetailsByEmail(final String email) {
        log.info("Getting user by email: " + email);
        final Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new UserException("Exception finding user by email: " + email));
        return mapToCustomerDetailsResponse(customer);
    }

    public List<CustomerDetailsResponse> getAllCustomerDetails() {
        List<CustomerDetailsResponse> response = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        customerList.stream().filter(Objects::nonNull).map(c -> mapToCustomerDetailsResponse(c)).forEach(c -> response.add(c));
        return response;
    }

    public Optional<Customer> getCustomerEntityById(final int id) {
        log.info("Getting user by ID: " + id);
        return customerRepository.findById(id);
    }

    private CustomerDetailsResponse mapToCustomerDetailsResponse(final Customer customer) {
        List<Booking> customerBookingEntities = bookingService.getBookingDetails(customer.getId());

        List<com.example.springtest.web.dto.responses.Booking> bookingList = new ArrayList<>();

        if (!customerBookingEntities.isEmpty()) {
            customerBookingEntities.stream().forEach(booking -> bookingList.add(com.example.springtest.web.dto.responses.Booking.builder()
                    .bookingReferenceId(booking.getBookingReference()).startDate(booking.getStartDate().toString())
                    .endDate(booking.getEndDate().toString())
                    .holidayName(holidayService.getHolidayDetails(booking.getHolidayId()).get().getHolidayName())
                    .build()));
        }

        return CustomerDetailsResponse.builder()
                .age(customer.getAge())
                .name(customer.getName())
                .email(customer.getEmail())
                .address(Address.builder()
                        .line1(customer.getAddress().getLine1())
                        .line2(customer.getAddress().getLine2())
                        .line3(customer.getAddress().getLine3())
                        .postcode(customer.getAddress().getPostcode())
                        .country(customer.getAddress().getCountry()).build())
                .bookings(bookingList)
                .build();
    }
}
