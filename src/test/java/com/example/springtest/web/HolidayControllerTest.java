package com.example.springtest.web;

import com.example.springtest.model.Country;
import com.example.springtest.services.HolidayService;
import com.example.springtest.web.controllers.HolidayController;
import com.example.springtest.web.dto.responses.HolidayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.example.springtest.helper.Helper.buildHolidayResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HolidayController.class)
public class HolidayControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HolidayService holidayService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void getAllHolidaysReturnsHolidayResponse() throws Exception {
        List<HolidayResponse> expected = new ArrayList<>();
        expected.add(buildHolidayResponse(Country.GREECE));
        expected.add(buildHolidayResponse(Country.ITALY));

        when(holidayService.getAllHolidays(any())).thenReturn(expected);

        mockMvc.perform(get("/holidays/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @Test
    public void getAllHolidaysWithFilterHolidayRequestInvalid() throws Exception {
        mockMvc.perform(get("/holidays/filtered")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" + "  \"maximumPrice\": 333333.0,\n" + "  \"country\": \"ITALY\"\n" + "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage", containsString("INVALID REQUEST: JSON parse error")))
                .andExpect(jsonPath("$.errorMessage", containsString("customerAge is marked non-null but is null")));
    }
}

