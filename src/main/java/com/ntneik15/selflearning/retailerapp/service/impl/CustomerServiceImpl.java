package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.customer.CustomerDto;
import com.ntneik15.selflearning.retailerapp.entity.Customer;
import com.ntneik15.selflearning.retailerapp.entity.Employee;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.mapper.CustomerMapper;
import com.ntneik15.selflearning.retailerapp.repository.ICustomerRepository;
import com.ntneik15.selflearning.retailerapp.repository.IEmployeeRepository;
import com.ntneik15.selflearning.retailerapp.service.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements ICustomerService {
    public final ICustomerRepository customerRepository;
    public final IEmployeeRepository employeeRepository;
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
        Integer maxCustomernumber = customerRepository.findMaxCustomernumber();
        customerDto.setCustomerNumber(String.valueOf(maxCustomernumber == null ? 1 : maxCustomernumber + 1));
        log.info("customerDto ={}", customerDto);
        return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customerDto)));
    }

    @Override
    public CustomerDto updatePartial(Integer customerNumber, CustomerDto customerDto) {
        log.info("PATCH customerDto ={}", customerDto);
        Customer customer = customerRepository.findByCustomernumber(customerNumber.longValue());
        if (customer == null) {
            throw new ConflictException("Product with product code: " + customerNumber + " not found");
        }
        Optional<Employee> salesRepEmployee = null;
        if (customerDto.getSalesRepEmployeeNumber() != null) {
            salesRepEmployee = employeeRepository.findById(customerDto.getSalesRepEmployeeNumber().longValue());
            if (salesRepEmployee.isEmpty()) {
                throw new ConflictException("Employee with employee number: " + customerDto.getSalesRepEmployeeNumber() + " not found");
            }
        }

        Customer result = Customer.builder()
                .customernumber(customer.getCustomernumber())
                .customername(customerDto.getCustomerName() == null ? customer.getCustomername() : customerDto.getCustomerName())
                .contactlastname(customerDto.getContactLastName() == null ? customer.getContactlastname() : customerDto.getContactLastName())
                .contactfirstname(customerDto.getContactFirstName() == null ? customer.getContactfirstname() : customerDto.getContactFirstName())
                .phone(customerDto.getPhone() == null ? customer.getPhone() : customerDto.getPhone())
                .addressline1(customerDto.getAddressLine1() == null ? customer.getAddressline1() : customerDto.getAddressLine1())
                .addressline2(customerDto.getAddressLine2() == null ? customer.getAddressline2() : customerDto.getAddressLine2())
                .city(customerDto.getCity() == null ? customer.getCity() : customerDto.getCity())
                .state(customerDto.getState() == null ? customer.getState() : customerDto.getState())
                .country(customerDto.getCountry() == null ? customer.getCountry() : customerDto.getCountry())
                .salesrepemployeenumber(salesRepEmployee == null ? customer.getSalesrepemployeenumber() : salesRepEmployee.get())
                .creditlimit(customerDto.getCreditLimit() == null ? customer.getCreditlimit() : BigDecimal.valueOf(customerDto.getCreditLimit()))
                .build();
        return customerMapper.toDto(customerRepository.save(result));
    }
}
