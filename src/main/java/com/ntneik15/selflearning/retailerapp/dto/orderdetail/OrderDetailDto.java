package com.ntneik15.selflearning.retailerapp.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntneik15.selflearning.retailerapp.entity.OrderdetailId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDto {
        @JsonIgnore
        OrderdetailId id;
        @NotNull(message = "Order number is required")
        private String orderNumber;
        @NotNull(message = "Product code is required")
        private String productCode;
        @NotNull(message = "Quantity ordered is required")
        private int quantityOrdered;
        @NotNull
        private double priceEach;
        @JsonIgnore
        private int orderLineNumber;
        @JsonIgnore
        public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
        }

}
