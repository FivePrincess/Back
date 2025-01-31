package com.example.guzip.entity.liked;

import com.example.guzip.entity.application.ApplicationForm;
import com.example.guzip.entity.user.Employer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LikedApplication")
public class LikedApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeApplicationId;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private ApplicationForm applicationForm;

    private LocalDateTime createdDate;

    @Column(nullable = false)
    private String password; // Consider hashing this!
}
