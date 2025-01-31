package com.example.guzip.service;

import com.example.guzip.dto.EmployeeInfoResponseDto;
import com.example.guzip.dto.EmployerInfoResponseDto;

public interface EmployerService {

    public EmployerInfoResponseDto getEmployerInfo(Long EmployeeId);

}
