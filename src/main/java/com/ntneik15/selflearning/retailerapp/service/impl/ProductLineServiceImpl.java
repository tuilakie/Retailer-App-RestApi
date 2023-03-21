package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.ProductLineDto;
import com.ntneik15.selflearning.retailerapp.mapper.ProductLineMapper;
import com.ntneik15.selflearning.retailerapp.repository.IProductlineRepository;
import com.ntneik15.selflearning.retailerapp.service.IProductLineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductLineServiceImpl implements IProductLineService {
    IProductlineRepository productlineRepository;
    @Override
    public List<ProductLineDto> getAllProductLines() {
        return productlineRepository.findAll().stream()
                .map(ProductLineMapper::toDto)
                .toList();
    }

    @Override
    public ProductLineDto getProductLine(String productLine) {
        return productlineRepository.findByProductlineIgnoreCase(productLine).stream()
                .map(ProductLineMapper::toDto)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ProductLineDto saveProductLine(ProductLineDto productLineDto) {
        return ProductLineMapper.toDto(productlineRepository.save(ProductLineMapper.toEntity(productLineDto)));
    }

    @Override
    public ProductLineDto updateProductLine(ProductLineDto productLineDto, String productLine) {
        return null;
    }

    @Override
    public ProductLineDto updateProductLineByPartially(ProductLineDto productLineDto, String productLine) {
        return null;
    }
}
