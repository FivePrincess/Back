package com.example.guzip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Guesthouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guesthouseId;

    @Column(nullable = false)
    private String guesthouseName;

    @Column(nullable = false)
    private String location;

    private String facilities;

    @Column(nullable = false)
    private String contact;
    //소개글
    private String intro;

    private double averageRating;

    @ElementCollection
    private List<String> hashtag;

    private String image;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;
}
