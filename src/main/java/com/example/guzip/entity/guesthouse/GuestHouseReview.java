package com.example.guzip.entity.guesthouse;
import com.example.guzip.entity.user.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GuestHouseReview")
public class GuestHouseReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false, precision = 3)
    private Double rating;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Foreign Key - Mapping to Employee entity

    @ManyToOne
    @JoinColumn(name = "guesthouse_id", nullable = false)
    private GuestHouse guestHouse; // Foreign Key - Mapping to GuestHouse entity
}
