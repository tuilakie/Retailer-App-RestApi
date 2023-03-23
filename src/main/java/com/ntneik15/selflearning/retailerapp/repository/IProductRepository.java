package com.ntneik15.selflearning.retailerapp.repository;

import com.ntneik15.selflearning.retailerapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<Product, Long> {
    Product findByProductcode(String productcode);
}
