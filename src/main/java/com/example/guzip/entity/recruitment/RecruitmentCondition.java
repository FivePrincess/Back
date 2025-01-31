package com.example.guzip.entity.recruitment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RecruitmentCondition")
public class RecruitmentCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitmentConditionId;

    @Column(nullable = false)
    private Date recruitmentStartDate;

    private Date recruitmentEndDate;

    private Date arrivalDate;

    private int femaleNumber;

    private int maleNumber;

    private int genderNeutralNumber;

    @Column(columnDefinition = "TEXT")
    private String preferredQualifications;

    private int minimumAge;

    private int maximumAge;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "recrutment_id", nullable = false)
    private Recruitment recruitment;
}
