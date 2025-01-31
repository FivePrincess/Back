package com.example.guzip.entity.guesthouse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GuestHouseImage")
public class GuestHouseImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "guesthouse_id", nullable = false)
    private GuestHouse guestHouse; // Foreign Key - Mapping to GuestHouse entity

    @Column(nullable = false)
    private LocalDateTime createdDate;
}
