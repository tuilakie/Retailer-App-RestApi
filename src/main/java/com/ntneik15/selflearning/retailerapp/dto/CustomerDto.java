package com.ntneik15.selflearning.retailerapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CustomerDto {
    private String customerNumber;
    @NotNull(message = "CustomerName cannot be null")
    private String customerName;
    @NotNull(message = "ContactLastName cannot be null")
    private String contactLastName;
    @NotNull(message = "ContactFirstName cannot be null")
    private String contactFirstName;
    @NotNull(message = "Phone cannot be null")
    private String phone;
    @NotNull(message = "AddressLine1 cannot be null")
    private String addressLine1;
    private String addressLine2;
    @NotNull(message = "City cannot be null")
    private String city;
    private String state;
    private String postalCode;
    @NotNull(message = "Country cannot be null")
    private String country;
    private Integer salesRepEmployeeNumber;
    private Double creditLimit;
}
