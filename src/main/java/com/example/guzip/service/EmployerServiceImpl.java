package com.example.guzip.service;

import com.example.guzip.dto.EmployeeInfoResponseDto;
import com.example.guzip.dto.EmployerInfoResponseDto;
import com.example.guzip.entity.user.Employee;
import com.example.guzip.entity.user.Employer;
import com.example.guzip.repository.EmployeeRepository;
import com.example.guzip.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    public EmployerInfoResponseDto getEmployerInfo(Long EmployerId) {
        Employer employer = employerRepository.findById(EmployerId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + EmployerId));

        // EmployerDto로 변환
        EmployerInfoResponseDto employerDto = EmployerInfoResponseDto.from(employer);

        return new EmployerInfoResponseDto(
                employerDto.employerId(),
                employerDto.businessNumber(),
                employerDto.createdDate(),
                employerDto.updatedDate(),
                employerDto.user() // User 엔티티 (또는 UserDto)
                // 필요한 경우 GuestHouse, Payment, LikedApplication DTO 리스트 추가
                // 예시: employerDto.getGuestHouses(), employerDto.getPayments(), employerDto.getLikedApplications()
        );
    }
}
