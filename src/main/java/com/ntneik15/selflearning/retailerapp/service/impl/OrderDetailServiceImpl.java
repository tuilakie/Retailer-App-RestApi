package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.orderdetail.OrderDetailDto;
import com.ntneik15.selflearning.retailerapp.dto.response.base.Pagination;
import com.ntneik15.selflearning.retailerapp.entity.Orderdetail;
import com.ntneik15.selflearning.retailerapp.mapper.OrderDetailMapper;
import com.ntneik15.selflearning.retailerapp.repository.IOrderDetailRepository;
import com.ntneik15.selflearning.retailerapp.service.IOrderDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    public final IOrderDetailRepository orderDetailRepository;
    public final OrderDetailMapper orderDetailMapper;
    @Override
    public Pair<List<OrderDetailDto>, Pagination> getAllWithPagination(int page, int size, String sort, String order) {
        if(order == null) order = "asc";
        Page<Orderdetail> orderDetailPage = orderDetailRepository.findAll(PageRequest.of(page, size, sort == null ? Sort.unsorted() : (order.equals("asc") ? Sort.by(sort).ascending() :
                (order.equals("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending()))));
        return Pair.of(orderDetailPage.map(OrderDetailMapper::toDto).getContent(), Pagination.createPaginationWithPageRequest(orderDetailPage, page, size));
    }

    @Override
    public List<OrderDetailDto> findByOrderNumber(String orderNumber) {
        return orderDetailRepository.findAllByOrdernumber(orderNumber).stream().map(OrderDetailMapper::toDto).toList();
    }

    @Override
    public List<OrderDetailDto> findByProductCode(String productCode) {
        return orderDetailRepository.findAllByProductcode(productCode).stream().map(OrderDetailMapper::toDto).toList();
    }

    @Override
    public OrderDetailDto create(OrderDetailDto orderDetailDto) {
        return OrderDetailMapper.toDto(orderDetailRepository.save(orderDetailMapper.toEntity(orderDetailDto)));
    }
}
