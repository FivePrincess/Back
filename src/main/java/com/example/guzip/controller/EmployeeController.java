package com.example.guzip.controller;

import com.example.guzip.dto.EmployeeInfoResponseDto;
import com.example.guzip.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/mypage")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employee_id}")
    public ResponseEntity<EmployeeInfoResponseDto> getEmployeeInfo(@PathVariable Long employee_id) {
        EmployeeInfoResponseDto employeeInfoResponse = employeeService.getEmployeeInfo(employee_id);
        return ResponseEntity.ok(employeeInfoResponse);
    }
}
