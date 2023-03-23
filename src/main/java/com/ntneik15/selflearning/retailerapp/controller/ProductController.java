package com.ntneik15.selflearning.retailerapp.controller;

import com.ntneik15.selflearning.retailerapp.dto.product.ProductDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.BaseResponse;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Meta;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.dto.response.base.PaginationResponse;
import com.ntneik15.selflearning.retailerapp.exception.BadRequestException;
import com.ntneik15.selflearning.retailerapp.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/products")
@AllArgsConstructor
@Slf4j
@Tag(name = "Product", description = "Product API")
@SecurityRequirement(name = "bearer-key")
public class ProductController {
    private final IProductService productService;

    @GetMapping()
    @Operation(summary = "Get all products")
    public ResponseEntity<BaseResponse> getAll(@RequestParam(required = false) Integer size,
                                               @RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) String sort,
                                               @RequestParam(required = false) String order) {

        if (size != null && page != null) {
            log.info("size {} and page {} is present with sort {} and order {}", size, page, sort, order);
            Pair<List<ProductDto>, Pagination> result = productService.getAllWithPagination(page, size, sort, order);
            return result != null ? ResponseEntity.ok(
                    new PaginationResponse(
                            new Meta(true, "get all product", HttpStatus.OK),
                            result.getFirst(),
                            result.getSecond())) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            log.info("get all product without pagination");
            List<ProductDto> products = productService.getAll();
            return ResponseEntity.ok(new BaseResponse(new Meta(true, "Get All Product", HttpStatus.OK), products));
        }
    }

    @GetMapping("/{productCode}")
    @Operation(summary = "Get product by product code")
    public ResponseEntity<BaseResponse> findByProductCode(@PathVariable String productCode) {
        return Optional.ofNullable(productService.findByProductCode(productCode))
                .map(product -> ResponseEntity.ok(new BaseResponse(new Meta(true, "Get product by product code: " + productCode, HttpStatus.OK), product)))
                .orElse(ResponseEntity.ok(new BaseResponse(new Meta(true, "Product not found with product code: " + productCode, HttpStatus.OK))));

    }

    @PostMapping()
    @Operation(summary = "create new product")
    public ResponseEntity<BaseResponse> save(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                throw new BadRequestException(error.getDefaultMessage());
            });
        }
        return ResponseEntity.ok(new BaseResponse(new Meta(true, "Save product", HttpStatus.OK), productService.save(productDto)));
    }

    @PutMapping("/{productCode}")
    @Operation(summary = "update product")
    public ResponseEntity<BaseResponse> update(@PathVariable(required = false) String productCode, @RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                throw new BadRequestException(error.getDefaultMessage());
            });
        }
        return ResponseEntity.ok(new BaseResponse(new Meta(true, "Update product", HttpStatus.OK), productService.update(productDto, productCode)));
    }

//    @PutMapping("")
//    public ResponseEntity<BaseResponse> update( @RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().forEach(error -> {
//                throw new BadRequestException(error.getDefaultMessage());
//            });
//        }
//        return ResponseEntity.ok(new BaseResponse(new Meta(true, "Update product", HttpStatus.OK), productService.update(productDto)));
//    }

    @PatchMapping("/{productCode}")
    @Operation(summary = "update product partially")
    public ResponseEntity<BaseResponse> updateProductByPartially(@PathVariable String productCode, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(new BaseResponse(new Meta(true, "Patch product", HttpStatus.OK), productService.updateProductByPartially(productDto, productCode)));
    }

    @DeleteMapping("/{productCode}")
    @Operation(summary = "delete product")
    public ResponseEntity<BaseResponse> delete(@PathVariable String productCode, @RequestHeader(value = "Authorization",required = false) String accessToken) {
        productService.delete(productCode, accessToken);
        return ResponseEntity.ok(new BaseResponse(new Meta(true, "Delete product with product code: " + productCode, HttpStatus.OK)));
    }

}
