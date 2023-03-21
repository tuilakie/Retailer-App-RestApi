package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.CustomerDto;
import com.ntneik15.selflearning.retailerapp.entity.Customer;
import com.ntneik15.selflearning.retailerapp.entity.Employee;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.repository.ICustomerRepository;
import com.ntneik15.selflearning.retailerapp.repository.IEmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerMapper {
    public final ICustomerRepository customerRepository;
    public final IEmployeeRepository employeeRepository;
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .customerNumber(String.valueOf(customer.getCustomernumber().intValue()))
                .customerName(customer.getCustomername())
                .contactLastName(customer.getContactlastname())
                .contactFirstName(customer.getContactfirstname())
                .phone(customer.getPhone())
                .addressLine1(customer.getAddressline1())
                .addressLine2(customer.getAddressline2())
                .city(customer.getCity())
                .state(customer.getState())
                .postalCode(customer.getPostalcode())
                .country(customer.getCountry())
                .salesRepEmployeeNumber(customer.getSalesrepemployeenumber().getId().intValue())
                .creditLimit(customer.getCreditlimit().doubleValue())
                .build();
    }

    public Customer toEntity(CustomerDto customerDto) {
        Optional< Employee > employee = employeeRepository.findById(customerDto.getSalesRepEmployeeNumber().longValue());
        if (employee.isEmpty())
            throw new ConflictException("Employee not found with id: " + customerDto.getSalesRepEmployeeNumber());

        return Customer.builder()
                .customernumber(Long.valueOf(customerDto.getCustomerNumber()))
                .customername(customerDto.getCustomerName())
                .contactlastname(customerDto.getContactLastName())
                .contactfirstname(customerDto.getContactFirstName())
                .phone(customerDto.getPhone())
                .addressline1(customerDto.getAddressLine1())
                .addressline2(customerDto.getAddressLine2())
                .city(customerDto.getCity())
                .state(customerDto.getState())
                .postalcode(customerDto.getPostalCode())
                .country(customerDto.getCountry())
                .salesrepemployeenumber(employee.get())
                .creditlimit(BigDecimal.valueOf(customerDto.getCreditLimit()))
                .build();
    }
}
