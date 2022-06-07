package com.example.springtest.web;

import com.example.springtest.services.HolidayService;
import com.example.springtest.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Holidays", description = "Holidays API")
@RestController
@SuppressWarnings("unused")
public class HolidayController {

    @Autowired
    HolidayService holidayService;

    @GetMapping("/holidays")
    @Operation(summary = "Get all holidays",
    responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = HolidayResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<HolidayResponse> getAllHolidays(@RequestParam Optional <Boolean> orderByPriceLowToHigh) {
        return holidayService.getAllHolidays(orderByPriceLowToHigh);
    }

    @GetMapping("/holidays/filtered")
    @Operation(summary = "Search for a specific holiday",
    responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = HolidayResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<HolidayResponse> getAllHolidays(@Valid @RequestBody HolidayFilter holidayFilter) {
        return holidayService.getFilteredHolidays(holidayFilter);
    }

    @PostMapping("/holidays/bookings/{holiday}")
    @Operation(summary = "Book holiday",
    responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void bookHoliday(@Valid @RequestBody final BookingRequest bookingRequest, @PathVariable final int holidayId) {
        holidayService.bookHoliday(bookingRequest);
    }

    @GetMapping("/holidays/bookings/")
    @Operation(summary = "Get all bookings",
    responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public List<BookingResponse> getAllBooking() {
        return holidayService.getAllBookings();
    }

    @PatchMapping("/holidays/bookings/{bookingId}")
    @Operation(summary = "Cancel a booking",
    responses = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public List<BookingResponse> cancelBooking() {
        return null; //not implemented yet
    }
}
