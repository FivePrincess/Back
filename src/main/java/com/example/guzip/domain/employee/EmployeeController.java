package com.example.guzip.domain.employee;

import com.example.guzip.dto.EmployeeMyPageResponseDTO;
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
    public ResponseEntity<EmployeeMyPageResponseDTO> getEmployeeInfo(@PathVariable Long employee_id) {
        EmployeeMyPageResponseDTO employeeMyPageResponse = employeeService.getEmployeeMyPage(employee_id);
        return ResponseEntity.ok(employeeMyPageResponse);
    }
}
