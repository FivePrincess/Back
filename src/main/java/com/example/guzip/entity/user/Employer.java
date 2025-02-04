package com.example.guzip.entity.user;

import com.example.guzip.entity.advertisement.PaidCustomer;
import com.example.guzip.entity.guesthouse.GuestHouse;
import com.example.guzip.entity.liked.LikedApplication;
import com.example.guzip.entity.advertisement.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private Long employerId;

    @Column(nullable = false, length = 12)
    private String businessNumber;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true) // unique=true 추가
    private User user; // Foreign Key - Mapping to User entity

    @OneToMany(mappedBy = "employer")
    private List<GuestHouse> guestHouses;

    @OneToMany(mappedBy = "employer")
    private List<Payment> payments;

    @OneToMany(mappedBy = "employer")
    private List<PaidCustomer> paidCustomers;

    @OneToMany(mappedBy = "employer")
    private List<LikedApplication> likedApplications;
}
