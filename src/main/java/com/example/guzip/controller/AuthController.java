package com.example.guzip.controller;

import com.example.guzip.dto.SignUpRequestDTO;
import com.example.guzip.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "사용자의 정보를 입력하여 회원가입을 진행합니다.")
    public ResponseEntity<Void> SignUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO);
        return ResponseEntity.ok().build();
    }
}
