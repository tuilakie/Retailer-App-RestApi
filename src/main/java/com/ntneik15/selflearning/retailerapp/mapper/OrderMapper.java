package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.order.OrderDto;
import com.ntneik15.selflearning.retailerapp.dto.order.StatusOrder;
import com.ntneik15.selflearning.retailerapp.entity.Customer;
import com.ntneik15.selflearning.retailerapp.entity.Order;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper {
    ICustomerRepository customerRepository;

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .orderNumber(order.getOrdernumber() != null ? order.getOrdernumber().intValue() : null)
                .orderDate(order.getOrderdate())
                .requiredDate(order.getRequireddate())
                .shippedDate(order.getShippeddate())
                .status(StatusOrder.getEnum(order.getStatus()))
                .comments(order.getComments())
                .customerNumber(order.getCustomernumber() != null ? order.getCustomernumber().getCustomernumber().toString() : null)
                .build();
    }

    public Order toEntity(OrderDto orderDto) {
        Customer customer = customerRepository.findByCustomernumber(orderDto.getCustomerNumber() != null ? Long.parseLong(orderDto.getCustomerNumber()) : null);
        if (customer == null)
            throw new ConflictException("Customer not found"
                    + orderDto.getCustomerNumber());

        return Order.builder()
                .ordernumber(orderDto.getOrderNumber() != null ? orderDto.getOrderNumber().longValue() : null)
                .orderdate(orderDto.getOrderDate())
                .requireddate(orderDto.getRequiredDate())
                .shippeddate(orderDto.getShippedDate())
                .status(orderDto.getStatus() != null ? orderDto.getStatus().toString() : null)
                .comments(orderDto.getComments())
                .customernumber(customer)
                .build();
    }
}
