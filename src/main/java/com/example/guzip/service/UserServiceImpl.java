package com.example.guzip.service;

import com.example.guzip.dto.EmployeeInfoResponseDto;
import com.example.guzip.dto.UserInfoResponseDto;
import com.example.guzip.entity.user.Employee;
import com.example.guzip.entity.user.User;
import com.example.guzip.repository.EmployeeRepository;
import com.example.guzip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("User not found with ID: " + userId));

        return new UserInfoResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getName(),
                user.getAge(),
                user.getRole(),
                user.getEmail(),
                user.getGender(),
                user.getNumber(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}
