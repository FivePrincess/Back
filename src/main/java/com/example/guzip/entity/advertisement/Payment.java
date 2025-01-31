package com.example.guzip.entity.advertisement;

import com.example.guzip.entity.user.Employer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(length = 50)
    private String method;

    @Column(nullable = false, precision = 10)
    private Double amount;

    @Column(length = 20)
    private String status;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer; // Foreign Key - No Entity Mapping for simplicity.  Consider Employer entity if needed.

    @Column(nullable = false)
    private String password2; // Consider hashing this!
}