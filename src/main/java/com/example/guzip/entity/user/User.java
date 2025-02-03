package com.example.guzip.entity.user;


import com.example.guzip.entity.identifier.UserRole;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 255)
    private String username;

    @Column(length = 255)
    private String password; // Consider hashing this!

    @Column(nullable = false, length = 255)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String profile;

    @Column(nullable = false, length = 255)
    private String name;

    private int age;

    private UserRole role;

    @Column(length = 1)
    private String gender;

    @Column(length = 13)
    private String number;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;
}
