package com.example.springtest.repository;

import com.example.springtest.database.HolidayRepository;
import com.example.springtest.model.Holiday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class HolidayRepositoryTest {

    @Autowired
    HolidayRepository holidayRepository;

    @Test
    void findHolidaysWithAgeAndPriceFilterReturnsHolidays() {
        List<Holiday> holidays = holidayRepository.findHolidaysWithFilter(25, new BigDecimal(1500.00));
        assertThat(holidays.size()).isEqualTo(2);
        assertThat(holidays.get(0).getPrice()).isLessThan(new Double(1500.00));
        assertThat(holidays.get(0).getMinimumAge()).isLessThan(25);
    }

    @Test
    void findHolidaysWithAgeAndPriceFilterReturnsFilteredList() {
        List<Holiday> holidays = holidayRepository.findHolidaysWithFilter(14, new BigDecimal(1500.00));
        assertTrue(holidays.isEmpty());
    }

    @Test
    void getHolidayByIdReturnsNull() {
        Optional<Holiday> holiday = holidayRepository.findById(55);
        assertTrue(holiday.isEmpty());
    }
}
