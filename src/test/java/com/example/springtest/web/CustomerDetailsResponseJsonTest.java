package com.example.springtest.web;

import com.example.springtest.web.dto.responses.CustomerDetailsResponse;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.example.springtest.helper.Helper.buildCustomerDetailsResponse;
import static com.example.springtest.helper.Helper.buildCustomerDetailsResponseWithBooking;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CustomerDetailsResponseJsonTest {
    @Autowired
    JacksonTester <CustomerDetailsResponse> jacksonTester;
    private final ClassPathResource responseNoBookings = new ClassPathResource("customerDetailsResponseNoBookings.json");
    private final ClassPathResource responseWithBookings = new ClassPathResource("customerDetailsResponseWithBookings.json");

    @Test
    void serializesResponseNoBookings() throws IOException {
        assertThat(jacksonTester.write(buildCustomerDetailsResponse())).isEqualToJson(responseNoBookings, JSONCompareMode.STRICT);
    }

    @Test
    void serializesResponseWithBookings() throws IOException {
        assertThat(jacksonTester.write(buildCustomerDetailsResponseWithBooking())).isEqualToJson(responseWithBookings, JSONCompareMode.STRICT);
    }
}
