package com.ntneik15.selflearning.retailerapp.repository;

import com.ntneik15.selflearning.retailerapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrderRepository extends JpaRepository<Order,Long> {
    Order findByOrdernumber(String ordernumber);
    @Query(value = "select max(o.ordernumber) from Order o")
    Integer findMaxOfOrdernumber();
}
