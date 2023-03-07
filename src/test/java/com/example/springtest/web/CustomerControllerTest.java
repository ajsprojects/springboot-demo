package com.example.springtest.web;

import com.example.springtest.exception.UserException;
import com.example.springtest.services.CustomerService;
import com.example.springtest.web.controllers.CustomerController;
import com.example.springtest.web.dto.responses.CustomerDetailsResponse;
import com.example.springtest.web.dto.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.example.springtest.helper.Helper.buildCustomerDetailsResponse;
import static com.example.springtest.helper.Helper.buildErrorResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    private static final String BASE_PATH = "/customer";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void getCustomerDetailsByIdReturnsResponse() throws Exception {
        CustomerDetailsResponse expected = buildCustomerDetailsResponse();
        when(customerService.getCustomerDetailsById(anyInt())).thenReturn(expected);

        mockMvc.perform(get(BASE_PATH + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void getCustomerDetailsByEmailReturnsResponse() throws Exception {
        CustomerDetailsResponse expected = buildCustomerDetailsResponse();
        when(customerService.getCustomerDetailsByEmail(anyString())).thenReturn(expected);

        mockMvc.perform(get(BASE_PATH + "/email/email@email.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void getCustomerDetailsByEmailReturnsNotFound() throws Exception {
        ErrorResponse expected = buildErrorResponse("NOT FOUND: Exception finding user: notfound@email.com");

        when(customerService.getCustomerDetailsByEmail("notfound@email.com")).thenThrow(new UserException("Exception finding user: notfound@email.com"));

        mockMvc.perform(get(BASE_PATH + "/email/notfound@email.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void getAllCustomerDetailReturnsMultipleUsers() throws Exception {
        List<CustomerDetailsResponse> expected = new ArrayList<>();
        expected.add(buildCustomerDetailsResponse());
        expected.add(buildCustomerDetailsResponse());

        when(customerService.getAllCustomerDetails()).thenReturn(expected);

        mockMvc.perform(get(BASE_PATH + "/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
}

