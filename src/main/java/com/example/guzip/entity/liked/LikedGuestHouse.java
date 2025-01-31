package com.example.guzip.entity.liked;
import com.example.guzip.entity.guesthouse.GuestHouse;
import com.example.guzip.entity.user.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LikedGuestHouse")
public class LikedGuestHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeHouseId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "guesthouse_id", nullable = false)
    private GuestHouse guestHouse;

    private LocalDateTime createdDate;
}
