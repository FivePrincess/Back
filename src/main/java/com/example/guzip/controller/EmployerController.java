package com.example.guzip.controller;

import com.example.guzip.dto.EmployerInfoResponse;
import com.example.guzip.service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employer/mypage")
public class EmployerController {

    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/{employer_id}")
    public ResponseEntity<EmployerInfoResponse> getEmployerInfo(@PathVariable Long employer_id) {
        EmployerInfoResponse employerInfoResponse = employerService.getEmployerInfo(employer_id);
        return ResponseEntity.ok(employerInfoResponse);
    }
}
