package com.ntneik15.selflearning.retailerapp.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class OrderDetailIdDto {
        private String orderNumber;
        private String productCode;

}
