package com.example.springtest.web.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    private final String errorMessage;
}
