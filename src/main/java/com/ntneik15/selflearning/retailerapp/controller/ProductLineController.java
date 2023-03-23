package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.productline.ProductLineDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.service.IProductLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product-lines")
@Tag(name = "Product Line", description = "Product Line API")
public class ProductLineController {
    private final IProductLineService productLineService;
    @Operation(summary ="Get all product lines")
    @GetMapping()
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(BaseResponse.builder()
                .data(productLineService.getAllProductLines())
                        .meta(new Meta(true, "Get all product line", HttpStatus.OK))
                .build());
    }
    @Operation(summary ="Get product line by product line code")
    @GetMapping("/{productLine}")
    public ResponseEntity<BaseResponse> getProductLine(@PathVariable String productLine) {
        ProductLineDto foundProductLine = productLineService.getProductLine(productLine);
        if (foundProductLine == null) {
            return ResponseEntity.ok(BaseResponse.builder()
                    .data(null)
                            .meta(new Meta(false, "Product line not found", HttpStatus.NOT_FOUND))
                    .build());
        }
        return ResponseEntity.ok(BaseResponse.builder()
                .data(foundProductLine)
                        .meta(new Meta(true, "Get product line with product line code: " + productLine, HttpStatus.OK))
                .build());
    }
//    @PostMapping()
//    public String create() {
//        return "create product line";
//    }
//    @PutMapping("/{productLineCode}")
//    public String update(@PathVariable String productLineCode) {
//        return "update product line with product line code: " + productLineCode;
//    }
//    @PatchMapping("/{productLineCode}")
//    public String patch(@PathVariable String productLineCode) {
//        return "patch product line with product line code: " + productLineCode;
//    }
//    @DeleteMapping("/{productLineCode}")
//    public String delete(@PathVariable String productLineCode) {
//        return "delete product line with product line code: " + productLineCode;
//    }
}
