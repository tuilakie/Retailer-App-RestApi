package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.payment.PaymentDto;
import com.ntneik15.selflearning.retailerapp.entity.Payment;
import com.ntneik15.selflearning.retailerapp.entity.PaymentId;

import java.math.BigDecimal;

public class PaymentMapper {
    public static PaymentDto toDto(Payment payment) {
        return PaymentDto.builder()
                .CheckNumber(payment.getId().getChecknumber())
                .CustomerNumber(payment.getId().getChecknumber())
                .PaymentDate(payment.getPaymentdate())
                .Amount(payment.getAmount().toString())
                .build();
    }

    public static Payment toEntity(PaymentDto paymentDto) {
        return Payment.builder()
                .id(PaymentId.builder()
                        .checknumber(paymentDto.getCheckNumber())
                        .customernumber(paymentDto.getCustomerNumber() == null ? null : Long.parseLong(paymentDto.getCustomerNumber()))
                        .build())
                .paymentdate(paymentDto.getPaymentDate())
                .amount(paymentDto.getAmount() == null ? null : new BigDecimal(paymentDto.getAmount()))
                .build();
    }
}
