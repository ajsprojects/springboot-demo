package com.example.springtest.web.controllers;

import com.example.springtest.services.BookingService;
import com.example.springtest.web.dto.requests.BookingRequest;
import com.example.springtest.web.dto.responses.BookingResponse;
import com.example.springtest.web.dto.responses.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Bookings", description = "Bookings API")
@RestController
@SuppressWarnings("unused")
@RequestMapping("bookings")
public class BookingsController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/")
    @Operation(summary = "Get all bookings", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookingResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    public List<BookingResponse> getAllBooking() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/book/{holidayId}")
    @Operation(summary = "Book holiday", responses = {
            @ApiResponse(responseCode = "200", description = "OK"), @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @ResponseStatus(value = HttpStatus.CREATED)
    public void bookHoliday(@Valid @RequestBody final BookingRequest bookingRequest, @PathVariable final int holidayId) {
        bookingService.bookHoliday(bookingRequest, holidayId);
    }

    @PatchMapping("/{bookingReference}")
    @Operation(summary = "Cancel a booking", responses = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity cancelBooking(@PathVariable final String bookingReference) {
        return bookingService.cancelBooking(bookingReference);
    }
}
