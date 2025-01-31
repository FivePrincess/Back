package com.example.guzip.entity.recruitment;
import com.example.guzip.entity.guesthouse.GuestHouse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Recruitment")
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recrutmentId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String hashtag;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = false, length = 255)
    private String contact;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String workLocation;

    @Column(columnDefinition = "TEXT")
    private String recruitmentDetails;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "guesthouse_id", nullable = false)
    private GuestHouse guestHouse;
}
