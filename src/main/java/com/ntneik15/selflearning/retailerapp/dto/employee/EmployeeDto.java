package com.ntneik15.selflearning.retailerapp.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EmployeeDto {
    private Integer employeeNumber;
    @NotNull(message = "LastName cannot be null")
    private String lastName;
    @NotNull(message = "FirstName cannot be null")
    private String firstName;
    @NotNull(message = "Extension cannot be null")
    private String extension;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "OfficeCode cannot be null")
    private String officeCode;
    private Integer reportsTo;
    @NotNull(message = "JobTitle cannot be null")
    private String jobTitle;
    @JsonIgnore
    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

}
