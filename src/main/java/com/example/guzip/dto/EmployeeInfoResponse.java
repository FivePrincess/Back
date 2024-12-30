package com.example.guzip.dto;

public record EmployeeInfoResponse(
        String employeeName,
        int age,
        String profile,
        String mbti,
        String instagram,
        String hashtag
) { }
