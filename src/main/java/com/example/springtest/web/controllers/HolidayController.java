package com.example.springtest.web.controllers;

import com.example.springtest.services.HolidayService;
import com.example.springtest.web.dto.responses.ErrorResponse;
import com.example.springtest.web.dto.requests.HolidayFilter;
import com.example.springtest.web.dto.responses.HolidayResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "Get all holidays", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = HolidayResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    public List<HolidayResponse> getAllHolidays(@RequestParam Optional<Boolean> orderByPriceLowToHigh) {
        return holidayService.getAllHolidays(orderByPriceLowToHigh);
    }

    @GetMapping("/holidays/filtered")
    @Operation(summary = "Search for a specific holiday", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = HolidayResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    public List<HolidayResponse> getHolidaysWithFilter(@Valid @RequestBody HolidayFilter holidayFilter) {
        return holidayService.getFilteredHolidays(holidayFilter);
    }
}
