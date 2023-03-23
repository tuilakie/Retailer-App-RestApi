package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.payment.PaymentDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IPaymentService {
    Pair<List<PaymentDto>, Pagination> getAllPaymentsWithPagination(Integer page, Integer size, String sort, String order);

    PaymentDto getPayment(String customerNumber, String checkNumber);

    List<PaymentDto> getPaymentsByCustomerNumber(String customerNumber);
}
