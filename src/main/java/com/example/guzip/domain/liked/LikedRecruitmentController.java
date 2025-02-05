package com.example.guzip.domain.liked;

import com.example.guzip.dto.LikedRecruitmentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/mypage/likes/recruitment")
public class LikedRecruitmentController {

    private final LikedRecruitmentService likedRecruitmentService;

    public LikedRecruitmentController(LikedRecruitmentService likedRecruitmentService) {
        this.likedRecruitmentService = likedRecruitmentService;
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<LikedRecruitmentResponseDTO> getLikedRecruitment(@PathVariable("employee_id") Long employeeId) {
        LikedRecruitmentResponseDTO likedRecruitmentResponse = likedRecruitmentService.getLikedRecruitment(employeeId);
        return ResponseEntity.ok(likedRecruitmentResponse);
    }

}
