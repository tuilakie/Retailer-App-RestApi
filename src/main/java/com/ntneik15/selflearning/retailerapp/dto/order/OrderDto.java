package com.ntneik15.selflearning.retailerapp.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Integer orderNumber;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private StatusOrder status;
    private String comments;
    private String customerNumber;
    @JsonIgnore
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}

