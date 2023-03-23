package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.payment.PaymentDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.dto.response.base.PaginationResponse;
import com.ntneik15.selflearning.retailerapp.service.IPaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/v1/payments")
@Tag(name = "Payment", description = "Payment API")
@SecurityRequirement(name = "bearer-key")
public class PaymentController {
    IPaymentService paymentService;

    @GetMapping()
    public ResponseEntity<PaginationResponse> getAllPayments(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order) {

        Pair<List<PaymentDto>, Pagination> paginationResult = paymentService.getAllPaymentsWithPagination(page == null ? 0 : page, size == null ? 10 : size, sort, order);
        return ResponseEntity.ok(new PaginationResponse(
                new Meta(true, "Get all payments successfully", HttpStatus.OK),
                paginationResult.getFirst(), paginationResult.getSecond()));
    }

    @GetMapping("/{CustomerNumber}")
    public ResponseEntity<BaseResponse> getPaymentByCheckNumber(@PathVariable String CustomerNumber, @RequestParam(required = false) String checkNumber) {
        if (checkNumber == null) return ResponseEntity.ok(new BaseResponse(
                new Meta(true, "Get payment with customerNumber:" + CustomerNumber + "successfully",
                        HttpStatus.OK),
                paymentService.getPaymentsByCustomerNumber(CustomerNumber)));
        return Optional.ofNullable(paymentService.getPayment(CustomerNumber, checkNumber))
                .map(payment -> ResponseEntity.ok(new BaseResponse(
                        new Meta(true,
                                "Get payment with (customerNumber, checkNumber): (" + CustomerNumber + ", " + checkNumber + ") successfully",
                                HttpStatus.OK),
                        payment)))
                .orElse(ResponseEntity.ok(new BaseResponse(
                        new Meta(true,
                                "Get payment with (customerNumber, checkNumber): (" + CustomerNumber + ", " + checkNumber + ") successfully",
                                HttpStatus.OK))));
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> createPayment() {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .meta(Meta.builder()
                                .Success(true)
                                .Message("Create payment successfully")
                                .Status(HttpStatus.OK)
                                .build())
                        .data(null)
                        .build()
        );
    }

}
