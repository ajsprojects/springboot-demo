package com.example.springtest.web.dto;


import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Jacksonized
@Builder
@Value
public class Customer {

    @NonNull
    @Size(min=1, max = 100)
    private Integer age;
    @NonNull
    @NotEmpty
    private String name;
    @NonNull
    @Size(min=4, max=100)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NonNull
    @NotEmpty
    private List<String> addressLines;
    @NonNull
    @Valid
    private PostCode postcode;
}