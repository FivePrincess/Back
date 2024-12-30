package com.example.guzip.controller;

import com.example.guzip.dto.EmployeeInfoResponse;
import com.example.guzip.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/mypage")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<EmployeeInfoResponse> getEmployeeInfo(@PathVariable Long employee_id) {
        EmployeeInfoResponse employeeInfoResponse = employeeService.getEmployeeInfo(employee_id);
        return ResponseEntity.ok(employeeInfoResponse);
    }
}
