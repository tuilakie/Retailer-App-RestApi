package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.payment.PaymentDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.entity.Payment;
import com.ntneik15.selflearning.retailerapp.entity.PaymentId;
import com.ntneik15.selflearning.retailerapp.mapper.PaymentMapper;
import com.ntneik15.selflearning.retailerapp.repository.IPaymentRepository;
import com.ntneik15.selflearning.retailerapp.service.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {
    public final IPaymentRepository paymentRepository;
    @Override
    public Pair<List<PaymentDto>, Pagination> getAllPaymentsWithPagination(Integer page, Integer size, String sort, String order) {
        if(order == null) order = "asc";
        Page<Payment> paymentPage = paymentRepository.findAll(PageRequest.of(page, size, sort == null ? Sort.unsorted() :
                (order.equals("asc") ? Sort.by(sort).ascending() :
                (order.equals("desc") ? Sort.by(sort).descending() :
                        Sort.by(sort).ascending()))));
        return Pair.of(paymentPage.map(PaymentMapper::toDto).getContent(), Pagination.createPaginationWithPageRequest(paymentPage, page, size));
    }

    @Override
    public PaymentDto getPayment(String customerNumber, String checkNumber) {
        return paymentRepository.findById(
                PaymentId.builder()
                        .checknumber(checkNumber)
                        .customernumber(Long.valueOf(customerNumber))
                        .build()).map(PaymentMapper::toDto).orElse(null);
        }

    @Override
    public List<PaymentDto> getPaymentsByCustomerNumber(String customerNumber) {
        return paymentRepository.findAllByCustomernumber(Integer.valueOf(customerNumber)).stream().map(PaymentMapper::toDto).toList();
    }

}
