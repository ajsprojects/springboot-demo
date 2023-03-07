package com.example.springtest.web.controllers;

import com.example.springtest.model.Customer;
import com.example.springtest.services.CustomerService;
import com.example.springtest.web.dto.responses.CustomerDetailsResponse;
import com.example.springtest.web.dto.responses.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer", description = "Customer API")
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),})
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerDetailsResponse getCustomerById(@PathVariable final int id) {
        return customerService.getCustomerDetailsById(id);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get customer by email", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerDetailsResponse getCustomerByEmail(@PathVariable final String email) {
        return customerService.getCustomerDetailsByEmail(email);
    }

    @GetMapping("/")
    @Operation(summary = "Get all customers", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerDetailsResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerDetailsResponse> getAllCustomers() {
        return customerService.getAllCustomerDetails();
    }
}
