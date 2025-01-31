package com.example.guzip.entity.guesthouse;

import com.example.guzip.entity.liked.LikedGuestHouse;
import com.example.guzip.entity.recruitment.Recruitment;
import com.example.guzip.entity.advertisement.Advertisement;
import com.example.guzip.entity.user.Employer;
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
@Table(name = "GuestHouse")
public class GuestHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guesthouseId;

    @Column(nullable = false, length = 255)
    private String guesthouseName;

    @Column(nullable = false, length = 255)
    private String location;

    @Column(nullable = false, length = 255)
    private String contact;

    @Column(nullable = false, length = 255)
    private String intro;

    @Column(nullable = false, length = 255)
    private String guesthouseDetails;

    @Column(nullable = false, precision = 3)
    private Double averageRating;

    private int ratingsCount;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer; // Foreign Key - Mapping to Employer entity

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "guestHouse") // Added for relationship with GuestHouseImage
    private List<GuestHouseImage> guestHouseImages;

    @OneToMany(mappedBy = "guestHouse") // Added for relationship with Recruitment
    private List<Recruitment> recruitments;

    @OneToMany(mappedBy = "guestHouse")
    private List<GuestHouseReview> guestHouseReviews;

    @OneToMany(mappedBy = "guestHouse")
    private List<Advertisement> advertisements;

    @OneToMany(mappedBy = "guestHouse")
    private List<LikedGuestHouse> likedGuestHouses;
}