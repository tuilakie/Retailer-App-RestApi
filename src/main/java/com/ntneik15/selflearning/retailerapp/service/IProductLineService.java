package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.ProductLineDto;

import java.util.List;

public interface IProductLineService {
    List<ProductLineDto> getAllProductLines();
    ProductLineDto getProductLine(String productLine);
    ProductLineDto saveProductLine(ProductLineDto productLineDto);
    ProductLineDto updateProductLine(ProductLineDto productLineDto, String productLine);
    ProductLineDto updateProductLineByPartially(ProductLineDto productLineDto, String productLine);
}
