package com.ntneik15.selflearning.retailerapp.dto.office;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeDto {
    private String officeCode;
    @NotNull(message = "City cannot be null")
    private String city;
    @NotNull(message = "Phone cannot be null")
    private String phone;
    @NotNull(message = "AddressLine1 cannot be null")
    private String addressLine1;
    private String addressLine2;
    private String state;
    @NotNull(message = "Country cannot be null")
    private String country;
    @NotNull(message = "PostalCode cannot be null")
    private String postalCode;
    @NotNull(message = "Territory cannot be null")
    private String territory;

}
