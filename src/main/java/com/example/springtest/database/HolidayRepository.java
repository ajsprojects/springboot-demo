package com.example.springtest.database;

import com.example.springtest.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

    @Query(value = "SELECT * FROM HOLIDAY hol WHERE :age >= hol.MINIMUM_AGE AND hol.PRICE <= :price", nativeQuery = true)
    List<Holiday> findHolidaysWithFilter(@Param("age") final int age, @Param("price") final BigDecimal priceLimit);
}
