package com.ntneik15.selflearning.retailerapp.repository;

import com.ntneik15.selflearning.retailerapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICustomerRepository extends JpaRepository <Customer, Long>{
     Customer findByCustomernumber(Long customernumber);

    @Query("SELECT MAX(c.customernumber) FROM Customer c")
     Integer findMaxCustomernumber();
}
