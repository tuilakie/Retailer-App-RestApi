package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.customer.CustomerDto;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.mapper.CustomerMapper;
import com.ntneik15.selflearning.retailerapp.repository.ICustomerRepository;
import com.ntneik15.selflearning.retailerapp.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    public final ICustomerRepository customerRepository;
    public final CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).toList();

    }

    @Override
    public CustomerDto getCustomerByCustomerNumber(Long id) {
        return customerRepository.findById(id).map(customerMapper::toDto).orElseThrow(
                () -> new ConflictException("Customer with id " + id + " not found"));

    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return null;
    }
}
