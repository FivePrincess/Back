package com.example.guzip.dto;

import com.example.guzip.entity.identifier.UserRole;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public record UserInfoResponseDto(
        Long userId,
        String username,
        String name,
        int age,
        UserRole role,
        String gender,
        String number,
        String email,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
){

}
