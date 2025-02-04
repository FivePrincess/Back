package com.example.guzip.domain.user;

import com.example.guzip.dto.UserInfoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/mypage")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@PathVariable Long user_id) {
        UserInfoResponseDto userInfo = userService.getUserInfo(user_id);
        return ResponseEntity.ok(userInfo);
    }
}
