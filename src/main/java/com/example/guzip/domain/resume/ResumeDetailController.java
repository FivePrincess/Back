package com.example.guzip.domain.resume;

import com.example.guzip.dto.ResumeDetailResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/employee/mypage/resume")
public class ResumeDetailController {

    private final ResumeDetailService resumeDetailService;

    public ResumeDetailController(ResumeDetailService resumeDetailService) {
        this.resumeDetailService = resumeDetailService;
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<ResumeDetailResponseDTO> getResumeDetail(@PathVariable("employee_id") long employeeId) {
        ResumeDetailResponseDTO resumeDetailResponse = resumeDetailService.getResumeDetail(employeeId);
        return ResponseEntity.ok(resumeDetailResponse);
    }
}
