package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.order.OrderDto;
import com.ntneik15.selflearning.retailerapp.dto.orderdetail.OrderDetailDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IOrderService {
    Pair<List<OrderDto>, Pagination> findAllOrders(int page, int size, String sort, String order);

    OrderDto findByOrder(Integer orderNumber);

    List<OrderDetailDto> createOrder(OrderDto orderDto, List<OrderDetailDto> orderDetailDtoList);
}
