package com.example.guzip.domain.user;

import com.example.guzip.dto.SignUpRequestDTO;
import com.example.guzip.dto.UserInfoResponseDto;

public interface UserService {

    void signUp(SignUpRequestDTO signUpRequestDTO); //회원가입 메소드 추가
    public UserInfoResponseDto getUserInfo(Long userId);

}
