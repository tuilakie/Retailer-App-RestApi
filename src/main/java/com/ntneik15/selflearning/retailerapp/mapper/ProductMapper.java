package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.ProductDto;
import com.ntneik15.selflearning.retailerapp.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class ProductMapper {

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .ProductCode(product.getProductcode())
                .ProductName(product.getProductname())
                .ProductLine(product.getProductline() != null ? ProductLineMapper.toDto(product.getProductline()): null)
                .ProductScale(product.getProductscale())
                .ProductVendor(product.getProductvendor())
                .ProductDescription(product.getProductdescription())
                .QuantityInStock(product.getQuantityinstock().intValue())
                .BuyPrice(product.getBuyprice().doubleValue())
                .MSRP(product.getMsrp().doubleValue())
                .build();
    }

    public static Product toEntity(ProductDto productDto) {
        return Product.builder()
                .productcode(productDto.getProductCode())
                .productname(productDto.getProductName())
                .productline(productDto.getProductLine() != null ? ProductLineMapper.toEntity(productDto.getProductLine()): null)
                .productscale(productDto.getProductScale())
                .productvendor(productDto.getProductVendor())
                .productdescription(productDto.getProductDescription())
                .quantityinstock(Integer.valueOf(productDto.getQuantityInStock()).shortValue())
                .buyprice(BigDecimal.valueOf(productDto.getBuyPrice()))
                .msrp(BigDecimal.valueOf(productDto.getMSRP()))
                .build();
    }

    public static Product toEntityUpdate(ProductDto productDto, Product oldProduct) {
        oldProduct.setProductname(productDto.getProductName());
        oldProduct.setProductline(productDto.getProductLine() != null ? ProductLineMapper.toEntity(productDto.getProductLine()): null);
        oldProduct.setProductscale(productDto.getProductScale());
        oldProduct.setProductvendor(productDto.getProductVendor());
        oldProduct.setProductdescription(productDto.getProductDescription());
        oldProduct.setQuantityinstock(Integer.valueOf(productDto.getQuantityInStock()).shortValue());
        oldProduct.setBuyprice(BigDecimal.valueOf(productDto.getBuyPrice()));
        oldProduct.setMsrp(BigDecimal.valueOf(productDto.getMSRP()));
        return oldProduct;
    }
}
