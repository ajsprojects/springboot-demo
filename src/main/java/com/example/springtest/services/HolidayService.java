package com.example.springtest.services;

import com.example.springtest.database.HolidayRepository;
import com.example.springtest.model.Country;
import com.example.springtest.model.Holiday;
import com.example.springtest.web.dto.requests.HolidayFilter;
import com.example.springtest.web.dto.responses.HolidayResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class HolidayService {
    HolidayRepository holidayRepository;

    public Optional<Holiday> getHolidayDetails(final int holidayId) {
        return holidayRepository.findById(holidayId);
    }

    public List<HolidayResponse> getAllHolidays(final Optional<Boolean> orderByPriceLowToHigh) {
        List<HolidayResponse> response = new ArrayList<>();

        List<Holiday> holidays = holidayRepository.findAll();

        holidays.stream().filter(Objects::nonNull).forEach(h -> response.add(buildHoliday(h)));

        if (orderByPriceLowToHigh.isPresent()) {
            response.sort(Comparator.comparing(HolidayResponse::getPrice));
        }
        return response;
    }

    public List<HolidayResponse> getFilteredHolidays(final HolidayFilter holidayFilter) {
        List<HolidayResponse> response = new ArrayList<>();

        List<Holiday> holidays = holidayRepository.findHolidaysWithFilter(holidayFilter.getCustomerAge(), holidayFilter.getPriceLimit());

        holidays.stream().filter(Objects::nonNull).filter(h -> {
            Country country = holidayFilter.getCountryChoice();
            if (Optional.ofNullable(country).isPresent()) {
                return holidayFilter.getCountryChoice().equals(h.getCountry()) ? true : false; //This could be included in the sql query...
            }
            return true;
        }).forEach(h -> response.add(buildHoliday(h)));
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
}

