package com.example.guzip.entity.liked;
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
@Table(name = "LikedAnnouncment")
public class LikedAnnouncment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeRecruitmentId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "recrutment_id", nullable = false)
    private Recruitment recruitment;

    private LocalDateTime createdDate;
}
