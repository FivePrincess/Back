package com.example.guzip.entity.recruitment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EmploymentCondition")
public class EmploymentCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workingConditionId;

    @Column(columnDefinition = "TEXT")
    private String workType;

    @Column(length = 255)
    private String employmentType;

    @Column(nullable = false, length = 255)
    private String employemntPeriod;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "recrutment_id", nullable = false)
    private Recruitment recruitment;
}
