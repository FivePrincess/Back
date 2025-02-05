package com.example.guzip.domain.resume;

import com.example.guzip.domain.employee.EmployeeRepository;
import com.example.guzip.dto.ResumeDetailResponseDTO;
import com.example.guzip.entity.application.Resume;
import com.example.guzip.entity.user.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeDetailService {

    private final EmployeeRepository employeeRepository;
    private final ResumeRepository resumeRepository;

    public ResumeDetailService(EmployeeRepository employeeRepository, ResumeRepository resumeRepository) {
        this.employeeRepository = employeeRepository;
        this.resumeRepository = resumeRepository;
    }

    public ResumeDetailResponseDTO getResumeDetail(Long employeeId) {
        Employee employee = getEmployee(employeeId);

        List<Resume> resumes = resumeRepository.findByEmployeeOrderByUpdatedDateDesc(employee);
        long resumesCount = resumeRepository.countByEmployee(employee);

        List<ResumeDetailResponseDTO.ResumeItemDTO> resumeItems = resumes.stream()
                .map(resume -> new ResumeDetailResponseDTO.ResumeItemDTO(resume.getResumeId(), resume.getTitle(), resume.getDescription()))
                .collect(Collectors.toList());

        return new ResumeDetailResponseDTO(employeeId, resumesCount, resumeItems);
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
