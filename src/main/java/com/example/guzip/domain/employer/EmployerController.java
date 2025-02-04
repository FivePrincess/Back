package com.example.guzip.domain.employer;

import com.example.guzip.dto.EmployerInfoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employer/mypage")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @GetMapping("/{employer_id}")
    public ResponseEntity<EmployerInfoResponseDto> getEmployerInfo(@PathVariable Long employer_id) {
        EmployerInfoResponseDto employerInfoResponse = employerService.getEmployerInfo(employer_id);
        return ResponseEntity.ok(employerInfoResponse);
    }
}
