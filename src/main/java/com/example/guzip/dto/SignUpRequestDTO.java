package com.example.guzip.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//회원가입 시 필요한 정보들
//아이디, 비밀번호, 이메일, 이름, 나이, 성별, 번호
public record SignUpRequestDTO(
        @NotBlank @Size(min = 4, max = 15) String username,
        @NotBlank @Size(min = 8, max = 15) String password,
        @NotBlank String confirmPassword, //비밀번호 확
        String email,
        @NotBlank String name,
        Integer age,
        @NotBlank String gender,
        @NotBlank String number,
        String businessNumber, //사장님 회원가입 시 사업자번호 입력
        String role // "EMPLOYEE" or "EMPLOYER"
) {
}
