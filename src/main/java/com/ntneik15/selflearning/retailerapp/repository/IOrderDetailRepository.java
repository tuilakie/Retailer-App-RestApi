package com.ntneik15.selflearning.retailerapp.repository;

import com.ntneik15.selflearning.retailerapp.entity.Orderdetail;
import com.ntneik15.selflearning.retailerapp.entity.OrderdetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<Orderdetail, OrderdetailId> {
    @Query("select o from Orderdetail o where o.id.ordernumber = ?1")
    public List<Orderdetail> findAllByOrdernumber(String orderNumber);
    @Query("select o from Orderdetail o where o.id.productcode = ?1")
    public List<Orderdetail> findAllByProductcode(String productCode);
    @Query("select count(o) from Orderdetail o where o.id.ordernumber = ?1")
    public Integer countByOrdernumber(String orderNumber);
}
