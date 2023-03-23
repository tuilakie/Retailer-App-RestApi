package com.ntneik15.selflearning.retailerapp.mapper;

import com.ntneik15.selflearning.retailerapp.dto.employee.EmployeeDto;
import com.ntneik15.selflearning.retailerapp.entity.Employee;
import com.ntneik15.selflearning.retailerapp.entity.Office;
import com.ntneik15.selflearning.retailerapp.exception.ConflictException;
import com.ntneik15.selflearning.retailerapp.repository.IEmployeeRepository;
import com.ntneik15.selflearning.retailerapp.repository.IOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    public final IOfficeRepository officeRepository;
    public final IEmployeeRepository employeeRepository;

    public static EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .employeeNumber(employee.getEmployeenumber() != null ? employee.getEmployeenumber().intValue() : null)
                .lastName(employee.getLastname())
                .firstName(employee.getFirstname())
                .extension(employee.getExtension())
                .email(employee.getEmail())
                .officeCode(employee.getOfficecode() != null ? employee.getOfficecode().getOfficecode() : null)
                .reportsTo(employee.getReportsto() != null ? employee.getReportsto() .getEmployeenumber().intValue() : null)
                .jobTitle(employee.getJobtitle())
                .build();
    }

    public Employee toEntity(EmployeeDto employeeDto) {
        Office office = officeRepository.findByOfficecode(employeeDto.getOfficeCode());
        Optional<Employee> employee = employeeRepository.findById(employeeDto.getReportsTo().longValue());
        if (office == null) throw new ConflictException("Office not found with code: " + employeeDto.getOfficeCode());
        if (employee.isEmpty())
            throw new ConflictException("Employee not found with id: " + employeeDto.getReportsTo());

        return Employee.builder()
                .employeenumber(employeeDto.getEmployeeNumber() != null ? Long.valueOf(employeeDto.getEmployeeNumber()) : null)
                .lastname(employeeDto.getLastName())
                .firstname(employeeDto.getFirstName())
                .extension(employeeDto.getExtension())
                .email(employeeDto.getEmail())
                .officecode(office)
                .reportsto(employee.get())
                .jobtitle(employeeDto.getJobTitle())
                .build();
    }
}
