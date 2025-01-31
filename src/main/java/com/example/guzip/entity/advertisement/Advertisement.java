package com.example.guzip.entity.advertisement;
import com.example.guzip.entity.guesthouse.GuestHouse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Advertisement")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId;

    @ManyToOne
    @JoinColumn(name = "guesthouse_id", nullable = false)
    private GuestHouse guestHouse;

    @OneToOne
    @JoinColumn(name = "subscribtion_id", nullable = false, unique = true)
    private PaidCustomer paidCustomer;
}
