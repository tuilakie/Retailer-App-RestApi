package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.customer.CustomerDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer", description = "Customer API")
public class CustomerController {
    private final ICustomerService customerService;
    @Operation(summary ="Get all customers")
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCustomer() {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Get all customers successfully")
                                .Status(HttpStatus.OK)
                                .build())
                        .data(customerService.getAllCustomers())
                        .build()
        );
    }
    @Operation(summary ="Get customer by customer number")
    @GetMapping("/{customerNumber}")
    public ResponseEntity<BaseResponse> getCustomerByCustomerNumber(@PathVariable Integer customerNumber) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Get customer by customerNumber: "+customerNumber+" successfully")
                                .Status(HttpStatus.OK)
                                .build())
                        .data(customerService.getCustomerByCustomerNumber(customerNumber.longValue()))
                        .build()
        );
    }
    @Operation(summary="Create new customer")
    @PostMapping("")
    public ResponseEntity<BaseResponse> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Create customer successfully")
                                .Status(HttpStatus.OK)
                                .build())
                        .data(customerService.createCustomer(customerDto))
                        .build()
        );
    }


}
