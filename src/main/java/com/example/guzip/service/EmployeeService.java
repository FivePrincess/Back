package com.example.guzip.service;

import com.example.guzip.dto.EmployeeInfoResponse;
import com.example.guzip.entity.Employee;
import com.example.guzip.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeInfoResponse getEmployeeInfo(Long EmployeeId) {
        Employee employee = employeeRepository.findById(EmployeeId)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found with ID: " + EmployeeId));

        return new EmployeeInfoResponse(
                employee.getEmployeeName(),
                employee.getAge(),
                employee.getProfile(),
                employee.getMbti(),
                employee.getInstagram(),
                employee.getHashtag()
        );
    }
}
