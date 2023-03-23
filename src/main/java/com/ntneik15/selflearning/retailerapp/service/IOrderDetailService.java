package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.orderdetail.OrderDetailDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IOrderDetailService {
    public Pair<List<OrderDetailDto>, Pagination> getAllWithPagination(int page, int size, String sort, String order);
    List<OrderDetailDto> findByOrderNumber(String orderNumber);
    List<OrderDetailDto> findByProductCode(String productCode);
    OrderDetailDto create(OrderDetailDto orderDetailDto);
}
