package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@Tag(name = "Employee", description = "Employee API")
public class EmployeeController {
    private final IEmployeeService employeeService;
    @Operation(summary ="Get all employees")
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllEmployee() {

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Get all employees successfully")
                                .Status(HttpStatus.OK)
                                .build())
                        .data(employeeService.getAllEmployees())
                        .build()
        );
    }
    @Operation(summary ="Get employee by employee number")
    @GetMapping("/{employeeNumber}")
    public ResponseEntity<BaseResponse> getEmployeeByEmployeeNumber(@PathVariable Integer employeeNumber) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Get employee by employeeNumber: "+employeeNumber+" successfully")
                                .Status(HttpStatus.OK)
                                .build())
                        .data(employeeService.getEmployeeById(employeeNumber.longValue()))
                        .build()
        );
    }

}
