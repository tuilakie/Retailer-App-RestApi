package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.ProductDto;
import com.ntneik15.selflearning.retailerapp.dto.request.auth.RoleDto;
import com.ntneik15.selflearning.retailerapp.dto.request.auth.UserDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.entity.Product;
import com.ntneik15.selflearning.retailerapp.entity.User;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.exception.UnauthorizedException;
import com.ntneik15.selflearning.retailerapp.mapper.ProductMapper;
import com.ntneik15.selflearning.retailerapp.mapper.UserMapper;
import com.ntneik15.selflearning.retailerapp.repository.IProductRepository;
import com.ntneik15.selflearning.retailerapp.security.jwt.JwtProvider;
import com.ntneik15.selflearning.retailerapp.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.data.domain.*;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final JwtProvider jwtProvider;
    private final UserServiceImpl userService;

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public Pair<List<ProductDto>, Pagination> getAllWithPagination(int page, int size, String sort, String order) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size, sort == null ? Sort.unsorted() : (order.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending())));

        return Pair.of(productPage.map(ProductMapper::toDto).getContent(), Pagination.createPaginationWithPageRequest(productPage, page, size));
    }

    @Override
    public ProductDto findByProductCode(String productCode) {
        return Optional.ofNullable(productRepository.findByProductcode(productCode)).map(ProductMapper::toDto).orElse(null);

    }

    @Override
    public ProductDto save(ProductDto productDto) {
        // find product by product code if exist throw exception
        if (productRepository.findByProductcode(productDto.getProductCode()) != null) {
            throw new ConflictException("Product with product code: " + productDto.getProductCode() + " already exist");
        }
        log.info("new product: " + productDto.getProductCode());
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(productDto)));
    }

    @Override
    public ProductDto update(ProductDto productDto, String productCode) {
        // find product by product code if exist throw exception
        Product oldProduct = productRepository.findByProductcode(productCode);
        if (oldProduct == null) {
            throw new ConflictException("Product with product code: " + productCode + " not found");
        }
        log.info("new product: " + oldProduct);
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntityUpdate(productDto, oldProduct)));
    }

    @Override
    public Boolean delete(String productCode, String accessToken) {
        if(accessToken == null) {
            throw new UnauthorizedException("Access token is null");
        }
        String username = jwtProvider.getUserNameFromJwtToken(accessToken.trim().replace("Bearer ", ""));
        userService.isUserHasRole(username,"ROLE_ADMIN");
        if (productRepository.findByProductcode(productCode) == null) {
            throw new ConflictException("Product with product code: " + productCode + " not found");
        }
        productRepository.delete(productRepository.findByProductcode(productCode));
        return true;
    }

    @Override
    public ProductDto updateProductByPartially(ProductDto productDto, String productCode) {
        // find product by product code if exist throw exception
        Product oldProduct = productRepository.findByProductcode(productCode);
        if (oldProduct == null) {
            throw new ConflictException("Product with product code: " + productCode + " not found");
        }
        ProductDto oldProductDto = ProductMapper.toDto(oldProduct);
        ProductDto result = ProductDto.builder().ProductCode(productDto.getProductCode() != null ? productDto.getProductCode() : oldProductDto.getProductCode()).ProductName(productDto.getProductName() != null ? productDto.getProductName() : oldProductDto.getProductName()).ProductScale(productDto.getProductScale() != null ? productDto.getProductScale() : oldProductDto.getProductScale()).ProductVendor(productDto.getProductVendor() != null ? productDto.getProductVendor() : oldProductDto.getProductVendor()).ProductDescription(productDto.getProductDescription() != null ? productDto.getProductDescription() : oldProductDto.getProductDescription()).QuantityInStock(productDto.getQuantityInStock() != null ? productDto.getQuantityInStock() : oldProductDto.getQuantityInStock()).BuyPrice(productDto.getBuyPrice() != null ? productDto.getBuyPrice() : oldProductDto.getBuyPrice()).MSRP(productDto.getMSRP() != null ? productDto.getMSRP() : oldProductDto.getMSRP()).ProductLine(productDto.getProductLine() != null ? productDto.getProductLine() : oldProductDto.getProductLine()).build();

        log.info("new product: " + result);
        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(result)));
    }
}
