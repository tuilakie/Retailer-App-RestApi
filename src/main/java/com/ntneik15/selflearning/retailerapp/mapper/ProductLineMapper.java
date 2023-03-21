package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.ProductLineDto;
import com.ntneik15.selflearning.retailerapp.entity.Productline;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductLineMapper {
    public static ProductLineDto toDto(Productline productline) {
        return ProductLineDto.builder()
                .productLine(productline.getProductline())
                .textDescription(productline.getTextdescription())
                .htmlDescription(productline.getHtmldescription())
                .image(productline.getImage())
                .build();
    }

    public static Productline toEntity(ProductLineDto productLineDto) {
        return Productline.builder()
                .productline(productLineDto.getProductLine())
                .textdescription(productLineDto.getTextDescription())
                .htmldescription(productLineDto.getHtmlDescription())
                .image(productLineDto.getImage())
                .build();
    }

    public static Productline toEntityUpdate(ProductLineDto productLineDto, Productline oldProductline) {
        oldProductline.setProductline(productLineDto.getProductLine());
        oldProductline.setTextdescription(productLineDto.getTextDescription());
        oldProductline.setHtmldescription(productLineDto.getHtmlDescription());
        oldProductline.setImage(productLineDto.getImage());
        return oldProductline;
    }
}
