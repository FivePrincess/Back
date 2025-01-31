package com.example.guzip.entity.advertisement;
import com.example.guzip.entity.user.Employer;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PaidCustomer")
public class PaidCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribtionId;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false, unique = true)
    private Employer employer;
}
