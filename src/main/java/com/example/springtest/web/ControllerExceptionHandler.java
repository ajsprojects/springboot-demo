package com.example.springtest.web;

import com.example.springtest.exception.HolidayException;
import com.example.springtest.exception.UserException;
import com.example.springtest.web.dto.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SuppressWarnings("unused")
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unhandledException(RuntimeException exception) {
        return ErrorResponse.builder().errorMessage("INTERNAL ERROR: " + exception.getLocalizedMessage()).build();
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserException exception) {
        return ErrorResponse.builder().errorMessage("NOT FOUND: " + exception.getLocalizedMessage()).build();
    }

    @ExceptionHandler(HolidayException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleHolidayNotFound(HolidayException exception) {
        return ErrorResponse.builder().errorMessage("HOLIDAY NOT FOUND: " + exception.getLocalizedMessage()).build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentNotValid(Exception exception) {
        return ErrorResponse.builder().errorMessage("INVALID REQUEST: " + exception.getLocalizedMessage()).build();
    }


}
