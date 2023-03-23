package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.customer.CustomerDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerByCustomerNumber(Long id);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updatePartial(Integer customerNumber, CustomerDto customerDto);
}
