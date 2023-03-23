package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.orderdetail.OrderDetailDto;
import com.ntneik15.selflearning.retailerapp.entity.Order;
import com.ntneik15.selflearning.retailerapp.entity.Orderdetail;
import com.ntneik15.selflearning.retailerapp.entity.OrderdetailId;
import com.ntneik15.selflearning.retailerapp.entity.Product;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.repository.IOrderRepository;
import com.ntneik15.selflearning.retailerapp.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderDetailMapper {
    public final IOrderRepository orderRepository;
    public final IProductRepository productRepository;
    public static OrderDetailDto toDto(Orderdetail orderdetail){
        return OrderDetailDto.builder()
                .id(orderdetail.getId())
                .orderNumber(orderdetail.getId().getOrdernumber() == null ? null : orderdetail.getId().getOrdernumber().toString())
                .productCode(orderdetail.getId().getProductcode())
                .quantityOrdered(orderdetail.getQuantityordered() == null ? null : orderdetail.getQuantityordered().intValue())
                .priceEach(orderdetail.getPriceeach() == null ? null: orderdetail.getPriceeach().doubleValue())
                .orderLineNumber(orderdetail.getOrderlinenumber() == null ? null : orderdetail.getOrderlinenumber().intValue())
                .build();
    }
    public Orderdetail toEntity(OrderDetailDto orderDetailDto){
        Order order = Optional.ofNullable(orderRepository.findByOrdernumber(orderDetailDto.getOrderNumber())).orElseThrow(
                () -> new ConflictException("Order with number " + orderDetailDto.getOrderNumber() + " does not exist"));
        Product product = Optional.ofNullable(productRepository.findByProductcode(orderDetailDto.getProductCode())).orElseThrow(
                () -> new ConflictException("Product with code " + orderDetailDto.getProductCode() + " does not exist"));
        return Orderdetail.builder()
                .id(OrderdetailId.builder()
                        .ordernumber(orderDetailDto.getOrderNumber() != null ? Long.valueOf(orderDetailDto.getOrderNumber()) : null)
                        .productcode(orderDetailDto.getProductCode())
                        .build())
                .ordernumber(order)
                .productcode(product)
                .quantityordered(Optional.ofNullable(orderDetailDto.getQuantityOrdered()) != null ? Long.valueOf(orderDetailDto.getQuantityOrdered()) : null)
                .priceeach(Optional.ofNullable(orderDetailDto.getPriceEach()) != null ? BigDecimal.valueOf(orderDetailDto.getPriceEach()) : null)
                .orderlinenumber(Optional.ofNullable(orderDetailDto.getOrderLineNumber()) != null ? Short.valueOf(orderDetailDto.getOrderLineNumber()+"") : null)
                .build();
    }
}
