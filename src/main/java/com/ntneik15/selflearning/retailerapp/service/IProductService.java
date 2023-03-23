package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.product.ProductDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductService {
    List<ProductDto> getAll();
    Pair<List<ProductDto>, Pagination> getAllWithPagination(int page, int size, String sort, String order);
    ProductDto findByProductCode(String productCode);
    ProductDto save(ProductDto productDto);
    ProductDto update(ProductDto productDto, String productCode);
    Boolean delete(String productCode, String accessToken);
    ProductDto updateProductByPartially(ProductDto productDto, String productCode);
}
