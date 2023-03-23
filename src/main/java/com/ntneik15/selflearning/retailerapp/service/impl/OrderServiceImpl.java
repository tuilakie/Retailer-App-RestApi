package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.order.OrderDto;
import com.ntneik15.selflearning.retailerapp.dto.orderdetail.OrderDetailDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.entity.Order;
import com.ntneik15.selflearning.retailerapp.entity.OrderdetailId;
import com.ntneik15.selflearning.retailerapp.mapper.OrderDetailMapper;
import com.ntneik15.selflearning.retailerapp.mapper.OrderMapper;
import com.ntneik15.selflearning.retailerapp.repository.IOrderDetailRepository;
import com.ntneik15.selflearning.retailerapp.repository.IOrderRepository;
import com.ntneik15.selflearning.retailerapp.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {
    IOrderRepository orderRepository;
    IOrderDetailRepository orderDetailRepository;
    OrderMapper orderMapper;
    OrderDetailMapper orderDetailMapper;

    @Override
    public Pair<List<OrderDto>, Pagination> findAllOrders(int page, int size, String sort, String order) {
        Page<Order> orderPage = orderRepository.findAll(
                PageRequest.of(page, size, sort == null ? Sort.unsorted() :
                        (order.equals("asc") ? Sort.by(sort).ascending() :
                                (order.equals("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending())))
        );
        return Pair.of(orderPage.map(OrderMapper::toDto).toList(), Pagination.createPaginationWithPageRequest(orderPage, page, size));
    }

    @Override
    public OrderDto findByOrder(Integer orderNumber) {
        return Optional.ofNullable(orderRepository.findByOrdernumber(orderNumber.toString()))
                .map(OrderMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<OrderDetailDto> createOrder(OrderDto orderDto, List<OrderDetailDto> orderDetailDtoList) {

        orderDto.setOrderNumber(orderRepository.findMaxOfOrdernumber() + 1);
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        log.info("Order created wit orderNumber: {}", order.getOrdernumber());
        List<OrderDetailDto> result = new ArrayList<>();

        orderDetailDtoList.stream().map(orderdetail -> {
            orderdetail.setOrderLineNumber(orderDetailRepository.countByOrdernumber(order.getOrdernumber().toString()));
            orderdetail.setOrderNumber(order.getOrdernumber().toString());
            orderdetail.setId(OrderdetailId.builder()
                    .ordernumber(order.getOrdernumber())
                    .productcode(orderdetail.getProductCode())
                    .build());
            return orderdetail;
        }).map(orderDetailMapper::toEntity).forEach(orderDetail -> {
            log.info("OrderDetail created wit orderNumber: {}", order.getOrdernumber());
            result.add(orderDetailMapper.toDto(orderDetailRepository.save(orderDetail)));
        });
        return null;
    }
}
