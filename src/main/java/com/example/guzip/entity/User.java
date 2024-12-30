package com.example.guzip.entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자(내부 시스템용) 자동 생성

    @Column(nullable = false, unique = true)
    private String loginId; //로그인용 아이디

    @Column(nullable = false)
    private String password; //비밀번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role; //역할
}
