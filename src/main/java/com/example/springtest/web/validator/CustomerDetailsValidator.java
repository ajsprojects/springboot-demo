package com.example.springtest.web.validator;

import com.example.springtest.model.Customer;
import com.example.springtest.services.CustomerService;
import com.example.springtest.web.dto.requests.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerDetailsValidator implements ConstraintValidator<CustomerDetailsMatch, CustomerRequest> {
    @Autowired
    private final CustomerService customerService;

    @Override
    public boolean isValid(CustomerRequest request, ConstraintValidatorContext context) {
        Optional<Customer> customer = customerService.getCustomerEntityById(request.getCustomerId());
        if (customer.isPresent()) {
            if (request.getName().equals(customer.get().getName()) && request.getEmail().equals(customer.get().getEmail()) && request.getAge().equals(customer.get().getAge())) {
                return true;
            }
        }
        return false;
    }
}