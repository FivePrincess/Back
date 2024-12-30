package com.example.guzip.service;

import com.example.guzip.dto.EmployerInfoResponse;
import com.example.guzip.entity.Employer;
import com.example.guzip.repository.EmployerRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {

    private EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public EmployerInfoResponse getEmployerInfo(Long EmployerId) {
        Employer employer = employerRepository.findById(EmployerId)
                .orElseThrow(()-> new IllegalArgumentException("Employer not found with ID: " + EmployerId));

        return new EmployerInfoResponse(
                employer.getEmployerName(),
                employer.getEmployerPhoneNumber()
        );
    }

}
