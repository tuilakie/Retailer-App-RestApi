package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.service.IOfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/offices")
@Tag(name = "Office", description = "Office API")
@SecurityRequirement(name = "bearer-key")
public class OfficeController {
    public final IOfficeService officeService;
    @Operation(summary ="Get all offices")
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllOffices() {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Get all offices successfully")
                                .Status(HttpStatus.OK)
                                .build()
                        )
                        .data(officeService.getAllOffices())
                        .build());
    }
    @Operation(summary ="Get office by office code")
    @GetMapping("/{officeCode}")
    public ResponseEntity<BaseResponse> getOfficeByOfficeCode(@PathVariable String officeCode) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Get office by office code successfully")
                                .Status(HttpStatus.OK)
                                .build()
                        )
                        .data(officeService.getOfficeByOfficeCode(officeCode))
                        .build());
    }

}
