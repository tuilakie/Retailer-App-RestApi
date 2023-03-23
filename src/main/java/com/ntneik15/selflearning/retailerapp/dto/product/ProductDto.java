package com.ntneik15.selflearning.retailerapp.dto.product;

import com.ntneik15.selflearning.retailerapp.dto.productline.ProductLineDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProductDto implements Serializable {
    private String ProductCode;
    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    private String ProductName;
    @NotNull(message = "Product line cannot be null")
    private ProductLineDto ProductLine;
    @NotNull(message ="product scale cannot be null")
    @NotBlank(message = "product scale cannot be blank")
    private String ProductScale;
    @NotNull(message = "ProductVendor cannot be null")
    @NotBlank(message = "ProductVendor cannot be blank")
    private String ProductVendor;
    @NotNull(message = "ProductDescription cannot be null")
    private String ProductDescription;
    @NotNull(message = "QuantityInStock cannot be null")
    @Size(min = 0, max = 32767, message = "QuantityInStock must be between 0 and 32767")
    private Integer QuantityInStock;
    @NotNull(message = "BuyPrice cannot be null")
    private Double BuyPrice;

    @NotNull(message = "MSRP cannot be null")
    private Double MSRP;

}
