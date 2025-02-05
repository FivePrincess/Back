package com.example.guzip.domain.liked;

import com.example.guzip.domain.employee.EmployeeRepository;
import com.example.guzip.dto.LikedRecruitmentResponseDTO;
import com.example.guzip.entity.liked.LikedAnnouncment;
import com.example.guzip.entity.user.Employee;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikedRecruitmentService {

    private final EmployeeRepository employeeRepository;
    private final LikedRecruitmentRepository likedRecruitmentRepository;

    public LikedRecruitmentService(EmployeeRepository employeeRepository, LikedRecruitmentRepository likedRecruitmentRepository) {
        this.employeeRepository = employeeRepository;
        this.likedRecruitmentRepository = likedRecruitmentRepository;
    }

    public LikedRecruitmentResponseDTO getLikedRecruitment(Long employeeId) {
        Employee employee = getEmployee(employeeId);

        List<LikedAnnouncment> likedRecruitment = likedRecruitmentRepository.findByEmployeeOrderByCreatedDateDesc(employee);
        long likedRecruitmentCount = likedRecruitmentRepository.countByEmployee(employee);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<LikedRecruitmentResponseDTO.RecruitmentLikedItemDTO> likedRecruitmentItems = likedRecruitment.stream()
                .map(likedAnnouncment -> new LikedRecruitmentResponseDTO.RecruitmentLikedItemDTO(
                        likedAnnouncment.getLikeRecruitmentId(),
                        likedAnnouncment.getCreatedDate().format(dateFormatter),
                        likedAnnouncment.getRecruitment().getGuestHouse().getGuesthouseName(),
                        likedAnnouncment.getRecruitment().getGuestHouse().getLocation(),
                        likedAnnouncment.getRecruitment().getGuestHouse().getContact()
                ))
                .collect(Collectors.toList());

        return new LikedRecruitmentResponseDTO(employeeId, likedRecruitmentCount, likedRecruitmentItems);
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Employee not found"));
    }

}
