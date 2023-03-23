package com.ntneik15.selflearning.retailerapp.repository;

import com.ntneik15.selflearning.retailerapp.entity.Payment;
import com.ntneik15.selflearning.retailerapp.entity.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment, PaymentId> {
    List<Payment> findAllByCustomernumber(Integer customerNumber);
}
