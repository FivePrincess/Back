package com.example.guzip.domain.employee;

import com.example.guzip.dto.EmployeeMyPageResponseDTO;

public interface EmployeeService {

    EmployeeMyPageResponseDTO getEmployeeMyPage(Long employeeId);

}
