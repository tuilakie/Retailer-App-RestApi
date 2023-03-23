package com.ntneik15.selflearning.retailerapp.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentDto {
    private String CheckNumber;
    @NotNull(message = "CustomerNumber is required")
    private String CustomerNumber;
    @NotNull(message = "PaymentDate is required")
    private LocalDate PaymentDate;
    @NotNull(message = "Amount is required")
    private String Amount;

    @JsonIgnore
    public void setCheckNumber(String checkNumber) {
        CheckNumber = checkNumber;
    }
}
