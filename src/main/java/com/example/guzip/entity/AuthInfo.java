package com.example.guzip.entity;
import com.example.guzip.entity.identifier.Provider;
import com.example.guzip.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AuthInfo")
public class AuthInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String providerId;

    @Column(length = 45)
    private Provider providerType;

    @Column(length = 255)
    private String snsName;

    @Column(length = 45)
    private String snsEmail;

    private LocalDateTime snsConnecteDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}