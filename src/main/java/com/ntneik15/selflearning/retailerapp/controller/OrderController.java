package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.order.OrderCreationDto;
import com.ntneik15.selflearning.retailerapp.dto.order.OrderDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.dto.response.base.PaginationResponse;
import com.ntneik15.selflearning.retailerapp.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Order", description = "Order API")
@SecurityRequirement(name = "bearer-key")
public class OrderController {
    public final IOrderService orderService;

    @GetMapping()
    @Operation(summary = "Get all orders")
    public ResponseEntity<PaginationResponse> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "ordernumber") String sort,
                                                           @RequestParam(defaultValue = "asc") String order) {
        Pair<List<OrderDto>, Pagination> paginationData =
                orderService.findAllOrders(page, size, sort, order);
        return ResponseEntity.ok(new PaginationResponse(Meta.builder()
                .Success(true).
                Message("Get all orders successfully")
                .build(),
                paginationData.getFirst(),
                paginationData.getSecond()));
    }
    @GetMapping("/{orderNumber}")
    @Operation(summary = "Get order by order number")
    public ResponseEntity<BaseResponse> getOrderByOrderNumber(@PathVariable Integer orderNumber) {
        return ResponseEntity.ok(new BaseResponse(null, orderService.findByOrder(orderNumber)));
    }

    @PostMapping()
    @Operation(summary = "Create new order")
    public ResponseEntity<BaseResponse> createOrder(@RequestBody OrderCreationDto orderCreationDto) {
        return ResponseEntity.ok(new BaseResponse(null, orderService.createOrder(orderCreationDto.getOrder(), orderCreationDto.getProductsList())));
    }
}
