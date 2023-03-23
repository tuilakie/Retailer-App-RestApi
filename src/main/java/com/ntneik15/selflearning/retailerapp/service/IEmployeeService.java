package com.ntneik15.selflearning.retailerapp.service;

import com.ntneik15.selflearning.retailerapp.dto.employee.EmployeeDto;

import java.util.List;

public interface IEmployeeService {
    public List<EmployeeDto> getAllEmployees();
    public EmployeeDto getEmployeeById(Long id);
}
