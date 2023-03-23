package com.ntneik15.selflearning.retailerapp.service.impl;

import com.ntneik15.selflearning.retailerapp.dto.employee.EmployeeDto;
import com.ntneik15.selflearning.retailerapp.entity.Employee;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.mapper.EmployeeMapper;
import com.ntneik15.selflearning.retailerapp.repository.IEmployeeRepository;
import com.ntneik15.selflearning.retailerapp.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    IEmployeeRepository employeeRepository;
    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeMapper::toDto).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee foundEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ConflictException("Employee with id " + id + " not found"));
        return EmployeeMapper.toDto(foundEmployee);
    }
}
