package com.example.springtest.web.dto.requests;


import com.example.springtest.web.dto.responses.Address;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;

@Jacksonized
@Builder
@Value
public class CustomerRequest {
    @NotNull
    Integer customerId;
    @NotNull
    @Min(18)
    @Max(99) Integer age;
    @NotNull
    @NotEmpty String name;
    @NotNull
    @Size(min = 4, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") String email;
    @NotNull
    Address address;
}