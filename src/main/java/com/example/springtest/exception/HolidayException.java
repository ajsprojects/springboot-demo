package com.example.springtest.exception;

public class HolidayException extends RuntimeException {
    public HolidayException(final String message) {
        super(message);
    }
}
