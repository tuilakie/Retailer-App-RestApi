package com.ntneik15.selflearning.retailerapp.dto.productline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class ProductLineDto implements Serializable {
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private byte[] image;

}
