package com.example.springtest.testdata;

import com.example.springtest.database.CustomerRepository;
import com.example.springtest.database.HolidayRepository;
import com.example.springtest.model.Country;
import com.example.springtest.model.Customer;
import com.example.springtest.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestData {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    HolidayRepository holidayRepository;

    public void setupTestData() {
        customerRepository.save(Customer.builder().age(12).email("tes1t@test.com").name("alex1").postcode("L13 N31").build());
        customerRepository.save(Customer.builder().age(21).email("test2@test.com").name("alex2").postcode("L13 N32").build());
        customerRepository.save(Customer.builder().age(43).email("test3@test.com").name("alex3").postcode("L13 N33").build());
        customerRepository.save(Customer.builder().age(88).email("test4@test.com").name("alex4").postcode("L13 N34").build());

        destinationRepository.save(Destination.builder().Country(Country.GREECE).resortAddress("1 Dubai Square").resortNotes("Lovely resort").build());
        destinationRepository.save(Destination.builder().Country(Country.ITALY).resortAddress("1 Italy Square").resortNotes("Lovely resort").build());
        destinationRepository.save(Destination.builder().Country(Country.SPAIN).resortAddress("1 Spain Square").resortNotes("Lovely resort").build());
        destinationRepository.save(Destination.builder().Country(Country.PHILLIPINES).resortAddress("1 Manila Square").resortNotes("Lovely resort").build());

        holidayRepository.save(Holiday.builder().minimumAge(18).description("perfect young holiday")
                .price(99.0).flightsIncluded(true).holidayName("The young one").destination(null);
    }


}
