package com.example.guzip.entity.application;
import com.example.guzip.entity.recruitment.Recruitment;
import com.example.guzip.entity.user.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ApplicationForm")
public class ApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;

    @Column(nullable = false, length = 255)
    private String employemntPeriod;

    @Column(length = 20)
    private String workingPeriod;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    private LocalDateTime openDate;

    @Column(nullable = false)
    private Boolean readStatus;

    private Boolean likeStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Foreign Key - Mapping to Employee entity

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume; // Foreign Key - Mapping to Resume entity

    @ManyToOne
    @JoinColumn(name = "recrutment_id", nullable = false)
    private Recruitment recruitment; // Foreign Key - Mapping to Recruitment entity


    @Column(nullable = false)
    private String password; // Consider hashing this!
}
