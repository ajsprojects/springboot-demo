package com.example.springtest.web.dto;

import com.example.springtest.web.validator.ValidPostCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class PostCode {
    @ValidPostCode
    final String postCode;
}
