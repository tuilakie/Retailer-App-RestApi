package com.ntneik15.selflearning.retailerapp.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntneik15.selflearning.retailerapp.entity.OrderdetailId;
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
        private String orderNumber;
        private String productCode;
        private int quantityOrdered;
        private double priceEach;
        @JsonIgnore
        private int orderLineNumber;
        @JsonIgnore
        public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
        }

}
