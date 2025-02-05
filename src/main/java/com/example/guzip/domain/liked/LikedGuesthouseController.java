package com.example.guzip.domain.liked;

import com.example.guzip.dto.LikedGuesthouseResponseDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/mypage/likes/guesthouse")
public class LikedGuesthouseController {

    private final LikedGuesthouseService likedGuesthouseService;

    public LikedGuesthouseController(LikedGuesthouseService likedGuesthouseService) {
        this.likedGuesthouseService = likedGuesthouseService;
    }

    @GetMapping("/{employee_id}") // ✅ PathVariable 적용
    public ResponseEntity<LikedGuesthouseResponseDTO> getLikedGuesthouse(@PathVariable("employee_id") Long employeeId) {
        LikedGuesthouseResponseDTO likedGuesthouseResponse = likedGuesthouseService.getLikedGuesthouse(employeeId);
        return ResponseEntity.ok(likedGuesthouseResponse);
    }

}
