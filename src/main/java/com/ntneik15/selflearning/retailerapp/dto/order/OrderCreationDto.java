package com.ntneik15.selflearning.retailerapp.dto.order;

import com.ntneik15.selflearning.retailerapp.dto.orderdetail.OrderDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreationDto {
    private OrderDto order;
    private List<OrderDetailDto> productsList;
}
