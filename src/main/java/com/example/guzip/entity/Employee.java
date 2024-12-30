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
@Entity
public class Employee extends User {

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false)
    private int age;

    private String profile;

    private String mbti;

    private String instagram;

    private String hashtag;
}

