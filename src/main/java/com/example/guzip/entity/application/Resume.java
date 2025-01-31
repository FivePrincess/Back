package com.example.guzip.entity.application;
import com.example.guzip.entity.user.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, length = 300)
    private String description;

    private LocalDateTime updatedAt;

    @Column(length = 255)
    private String address;

    @Column(nullable = false, length = 255)
    private String phoneNumber;

    @Column(length = 4)
    private String mbti;

    @Column(length = 255)
    private String instagram;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "resume")
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "resume")
    private List<ApplicationForm> applicationForms;
}
