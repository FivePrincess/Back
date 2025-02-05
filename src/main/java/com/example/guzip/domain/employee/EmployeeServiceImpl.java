package com.example.guzip.domain.employee;

import com.example.guzip.domain.applicationForm.ApplicationFormRepository;
import com.example.guzip.domain.liked.LikedGuesthouseRepository;
import com.example.guzip.domain.liked.LikedRecruitmentRepository;
import com.example.guzip.domain.resume.ResumeRepository;
import com.example.guzip.dto.EmployeeMyPageResponseDTO;
import com.example.guzip.entity.application.ApplicationForm;
import com.example.guzip.entity.application.Resume;
import com.example.guzip.entity.liked.LikedAnnouncment;
import com.example.guzip.entity.liked.LikedGuestHouse;
import com.example.guzip.entity.user.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ResumeRepository resumeRepository;
    private final ApplicationFormRepository applicationFormRepository;
    private final LikedRecruitmentRepository likedRecruitmentRepository;
    private final LikedGuesthouseRepository likedGuesthouseRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ResumeRepository resumeRepository, ApplicationFormRepository applicationFormRepository, LikedRecruitmentRepository likedRecruitmentRepository, LikedGuesthouseRepository likedGuesthouseRepository) {
        this.employeeRepository = employeeRepository;
        this.resumeRepository = resumeRepository;
        this.applicationFormRepository = applicationFormRepository;
        this.likedRecruitmentRepository = likedRecruitmentRepository;
        this.likedGuesthouseRepository = likedGuesthouseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeMyPageResponseDTO getEmployeeMyPage(Long employeeId) {
        Employee employee = getEmployee(employeeId);

        List<Resume> resumes = resumeRepository.findTop2ByEmployeeOrderByUpdatedDateDesc(employee);
        List<ApplicationForm> applicationForms = applicationFormRepository.findTop2ByEmployeeOrderByUpdatedDateDesc(employee);
        List<LikedAnnouncment> likedRecruitments = likedRecruitmentRepository.findTop2ByEmployeeOrderByCreatedDateDesc(employee);
        List<LikedGuestHouse> likedGuesthouses = likedGuesthouseRepository.findTop2ByEmployeeOrderByCreatedDateDesc(employee);

        long resumeCount = resumeRepository.countByEmployee(employee);
        long applicationFormCount = applicationFormRepository.countByEmployee(employee);
        long likedRecruitmentCount = likedRecruitmentRepository.countByEmployee(employee);
        long likedGuesthouseCount = likedGuesthouseRepository.countByEmployee(employee);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<EmployeeMyPageResponseDTO.ResumeDTO> resumeDTOs = resumes.stream()
                .map(r -> new EmployeeMyPageResponseDTO.ResumeDTO(r.getResumeId(), r.getTitle(), r.getDescription()))
                .toList();

        List<EmployeeMyPageResponseDTO.ApplicationDTO> applicationDTOs = applicationForms.stream()
                .map(a -> new EmployeeMyPageResponseDTO.ApplicationDTO(
                        a.getRecruitment().getRecrutmentId(),
                        a.getCreatedDate().format(dateFormatter),
                        a.getRecruitment().getGuestHouse().getGuesthouseName(),
                        a.getRecruitment().getIntroduction()
                ))
                .toList();

        List<EmployeeMyPageResponseDTO.ScrapGuesthouse> scrapGuesthouseDTOs = likedGuesthouses.stream()
                .map(lg -> new EmployeeMyPageResponseDTO.ScrapGuesthouse(
                        lg.getGuestHouse().getGuesthouseId(),
                        lg.getCreatedDate().format(dateFormatter),
                        lg.getGuestHouse().getGuesthouseName(),
                        lg.getGuestHouse().getLocation(),
                        lg.getGuestHouse().getContact()
                ))
                .toList();

        List<EmployeeMyPageResponseDTO.ScrapAnnouncement> scrapAnnouncementDTOs = likedRecruitments.stream()
                .map(lr -> new EmployeeMyPageResponseDTO.ScrapAnnouncement(
                        lr.getRecruitment().getRecrutmentId(),
                        lr.getCreatedDate().format(dateFormatter),
                        lr.getRecruitment().getGuestHouse().getGuesthouseName(),
                        lr.getRecruitment().getIntroduction()
                ))
                .toList();

        return new EmployeeMyPageResponseDTO(
                true,
                new EmployeeMyPageResponseDTO.Data(
                        new EmployeeMyPageResponseDTO.Profile(
                                employee.getEmployeeId(),
                                employee.getUser().getName(),
                                employee.getUser().getGender(),
                                employee.getUser().getAge()
                        ),
                        new EmployeeMyPageResponseDTO.Count(
                                resumeCount,
                                applicationFormCount,
                                likedRecruitmentCount,
                                likedGuesthouseCount
                        ),
                        resumeDTOs,
                        applicationDTOs,
                        new EmployeeMyPageResponseDTO.Scrap(
                                scrapGuesthouseDTOs,
                                scrapAnnouncementDTOs
                        )
                )
        );
    }

    private Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found"));
    }
}