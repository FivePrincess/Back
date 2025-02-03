package com.example.guzip.controller;

import com.example.guzip.dto.LoginRequestDTO;
import com.example.guzip.dto.SignUpRequestDTO;
import com.example.guzip.security.CustomUserDetails;
import com.example.guzip.security.JWTUtil;
import com.example.guzip.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "사용자의 정보를 입력하여 회원가입을 진행합니다.")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        userService.signUp(signUpRequestDTO);
        return ResponseEntity.ok().build();
    }

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "로그인 및 JWT 토큰 발급", description = "사용자 이름과 비밀번호를 입력하여 JWT 토큰을 발급받습니다.")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        // 1. AuthenticationManager 를 통해 인증을 수행
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password()));

        // 2. 인증된 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        //3. 사용자 권한 가져오기
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("권한이 존재하지 않습니다."));

        //4. JWT 토큰 생성
        String token = jwtUtil.createJWT(userDetails.getUsername(),role, 60 * 60 * 10L);

        //5.  JWT 토큰을 응답 헤더 및 JSON 형식으로 반환
        return ResponseEntity.ok().header("Authorization", "Bearer " + token).build();
    }
}
