package com.ntneik15.selflearning.retailerapp.repository;

import com.ntneik15.selflearning.retailerapp.entity.Productline;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductlineRepository extends JpaRepository<Productline, Long> {
    List<Productline> findByProductlineIgnoreCase(String productline);
}
