package com.example.guzip.domain.applicationForm;

import com.example.guzip.domain.employee.EmployeeRepository;
import com.example.guzip.dto.ApplicationDetailResponseDTO;
import com.example.guzip.entity.application.ApplicationForm;
import com.example.guzip.entity.user.Employee;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationDetailService {

    private final EmployeeRepository employeeRepository;
    private final ApplicationFormRepository applicationFormRepository;

    public ApplicationDetailService(EmployeeRepository employeeRepository, ApplicationFormRepository applicationFormRepository) {
        this.employeeRepository = employeeRepository;
        this.applicationFormRepository = applicationFormRepository;
    }

    public ApplicationDetailResponseDTO getApplicationDetail(Long employeeId) {
        Employee employee = getEmployee(employeeId);

        List<ApplicationForm> applicationForms = applicationFormRepository.findByEmployeeOrderByUpdatedDateDesc(employee);
        long applicationFormCount = applicationFormRepository.countByEmployee(employee);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<ApplicationDetailResponseDTO.ApplicationItemDTO> applicationItems = applicationForms.stream()
                .map(application -> new ApplicationDetailResponseDTO.ApplicationItemDTO(
                        application.getAppId(),
                        application.getCreatedDate().format(dateFormatter),
                        application.getRecruitment().getGuestHouse().getGuesthouseName(),
                        application.getResume().getTitle(),
                        application.getReadStatus()
                ))
                .collect(Collectors.toList());

        return new ApplicationDetailResponseDTO(employeeId, applicationFormCount, applicationItems);
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
