package com.example.guzip.entity.application;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WorkExperience")
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workexperienceId;

    @Column(nullable = false, length = 255)
    private String workplaceName;

    private LocalDateTime startDate;

    @Column(name = "end-date")
    private LocalDateTime endDate;

    @Column(columnDefinition = "TEXT")
    private String workContent;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;
}
