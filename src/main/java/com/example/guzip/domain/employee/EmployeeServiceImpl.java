package com.example.guzip.domain.employee;

import com.example.guzip.dto.EmployeeInfoResponseDto;
import com.example.guzip.entity.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeInfoResponseDto getEmployeeInfo(Long EmployeeId) {
        Employee employee = employeeRepository.findById(EmployeeId)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found with ID: " + EmployeeId));

        return new EmployeeInfoResponseDto(
                employee.getMbti(),
                employee.getInstagram(),
                employee.getHashtag()
        );
    }
}