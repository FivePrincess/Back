package com.example.guzip.domain.applicationForm;

import com.example.guzip.dto.ApplicationDetailResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee/mypage/application")
public class ApplicationDetailController {

    private final ApplicationDetailService applicationDetailService;

    public ApplicationDetailController(ApplicationDetailService applicationDetailService) {
        this.applicationDetailService = applicationDetailService;
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<ApplicationDetailResponseDTO> getApplicationDetail(@PathVariable("employee_id") Long employeeId) {
        ApplicationDetailResponseDTO applicationDetailResponse = applicationDetailService.getApplicationDetail(employeeId);
        return ResponseEntity.ok(applicationDetailResponse);
    }
}
