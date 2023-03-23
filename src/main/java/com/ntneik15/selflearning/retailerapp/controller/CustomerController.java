package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.customer.CustomerDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.exception.BadRequestException;
import com.ntneik15.selflearning.retailerapp.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer", description = "Customer API")
@SecurityRequirement(name = "bearer-key")
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
    public ResponseEntity<BaseResponse> createCustomer(@RequestBody @Valid CustomerDto customerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new BadRequestException(errors.toString());
        }
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
    @PatchMapping("/{customerNumber}")
    @Operation(summary="Update customer by customer number")
    public ResponseEntity<BaseResponse> updatePartial (@PathVariable Integer customerNumber, @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                throw new BadRequestException(error.getDefaultMessage());
            });
        }
        log.info("Update customer with customerNumber: "+customerNumber);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Update customer successfully")
                                .Status(HttpStatus.OK)
                                .build())
                        .data(customerService.updatePartial(customerNumber, customerDto))
                        .build()
        );
    }

}
