package com.example.springtest.web.dto.responses;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    String errorMessage;
}
