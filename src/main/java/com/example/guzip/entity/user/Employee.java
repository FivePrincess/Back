package com.example.guzip.entity.user;

import com.example.guzip.entity.application.ApplicationForm;
import com.example.guzip.entity.application.Resume;
import com.example.guzip.entity.guesthouse.GuestHouseReview;
import com.example.guzip.entity.liked.LikedAnnouncment;
import com.example.guzip.entity.liked.LikedGuestHouse;
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
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(length = 4)
    private String mbti;

    @Column(length = 255)
    private String instagram;

    @Column(length = 255)
    private String hashtag;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToOne  // Or @ManyToOne if a User can have multiple Employee profiles
    @JoinColumn(name = "user_id", nullable = false, unique = true) // unique=true 추가
    private User user; // Foreign Key - Mapping to User entity

    @OneToMany(mappedBy = "employee")
    private List<GuestHouseReview> guestHouseReviews;

    @OneToMany(mappedBy = "employee")
    private List<ApplicationForm> applicationForms;

    @OneToMany(mappedBy = "employee")
    private List<LikedAnnouncment> likedAnnouncments;

    @OneToMany(mappedBy = "employee")
    private List<LikedGuestHouse> likedGuestHouses;

    @OneToMany(mappedBy = "employee")
    private List<Resume> resumes;
}

