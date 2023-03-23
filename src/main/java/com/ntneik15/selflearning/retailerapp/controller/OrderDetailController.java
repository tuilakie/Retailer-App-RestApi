package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.orderdetail.OrderDetailDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.dto.response.base.PaginationResponse;
import com.ntneik15.selflearning.retailerapp.service.IOrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order-detail")
@Tag(name = "Order Detail", description = "Order Detail API")
public class OrderDetailController {
    public final IOrderDetailService orderDetailService;

    @GetMapping("/order")
    @Operation(summary = "Get all order detail")
    public ResponseEntity<PaginationResponse> getAllOrderDetail(@RequestParam(required = false) Integer size,
                                                                @RequestParam(required = false) Integer page,
                                                                @RequestParam(required = false) String sort,
                                                                @RequestParam(required = false) String order) {
        Pair<List<OrderDetailDto>, Pagination> paginationData =
                orderDetailService.getAllWithPagination(page == null ? 0 : page, size == null ? 10 : size, sort, order);
        return ResponseEntity.ok(new PaginationResponse(
                new Meta(true, "get all product", HttpStatus.OK),
                paginationData.getFirst(),
                paginationData.getSecond()));
    }
    @GetMapping("/order/{OrderNumber}")
    @Operation(summary = "Get all order detail by order number")
    public ResponseEntity<BaseResponse> findAllByOrderNumber(@PathVariable String OrderNumber) {
        return Optional.ofNullable(orderDetailService.findByOrderNumber(OrderNumber))
                .map(orderDetail -> ResponseEntity.ok(new BaseResponse(new Meta(true, "Get all order detail by ordernumber: " + OrderNumber, HttpStatus.OK), orderDetail)))
                .orElse(ResponseEntity.ok(new BaseResponse(new Meta(true, "Order detail not found with ordernumber: " + OrderNumber, HttpStatus.OK))));
    }

    @GetMapping("product/{ProductCode}")
    @Operation(summary = "Get all order detail by product code")
    public ResponseEntity<BaseResponse> findByProductCode(@PathVariable String ProductCode) {
        return Optional.ofNullable(orderDetailService.findByProductCode(ProductCode))
                .map(orderDetail -> ResponseEntity.ok(new BaseResponse(new Meta(true, "Get all order detail by ProductCode: " + ProductCode, HttpStatus.OK), orderDetail)))
                .orElse(ResponseEntity.ok(new BaseResponse(new Meta(true, "Order detail not found with ProductCode: " + ProductCode, HttpStatus.OK))));
    }
}

