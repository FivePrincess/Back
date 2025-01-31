package com.example.guzip.service;

import com.example.guzip.dto.UserInfoResponseDto;

public interface UserService {
    public UserInfoResponseDto getUserInfo(Long userId);
}
