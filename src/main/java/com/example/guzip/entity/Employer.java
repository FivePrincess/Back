package com.example.guzip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employer extends User {

    @Column(nullable = false)
    private String employerName;

    @Column(nullable = false)
    private String employerPhoneNumber ;

    @Column(nullable = false, unique = true)
    private String businessLicenseNumber;

    //guesthouse랑 1대 다 관계
    @OneToMany(mappedBy = "employer")
    private List<Guesthouse> guestHouses = new ArrayList<>();
}
