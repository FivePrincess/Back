package com.example.guzip.service;

import com.example.guzip.dto.GuesthouseRequestDto;
import com.example.guzip.entity.guesthouse.GuestHouse;
import com.example.guzip.entity.user.Employer;
import com.example.guzip.repository.EmployerRepository;
import com.example.guzip.repository.GuesthouseRepository;
import org.springframework.stereotype.Service;

@Service
public class GuesthouseSerivce {

    private GuesthouseRepository guesthouseRepository;
    private EmployerRepository employerRepository;

    public GuesthouseSerivce(GuesthouseRepository guesthouseRepository, EmployerRepository employerRepository) {
        this.guesthouseRepository = guesthouseRepository;
        this.employerRepository = employerRepository;
    }

    public GuestHouse registerGuestHouse(Long employerId, GuesthouseRequestDto guesthouseRequest) {

        //Employer 조회
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new IllegalArgumentException("employer not found"));

        GuestHouse guesthouse = new GuestHouse();

        guesthouse.setGuesthouseName(guesthouseRequest.guesthouseName());
        guesthouse.setLocation(guesthouseRequest.location());
        guesthouse.setContact(guesthouseRequest.contact());
        guesthouse.setIntro(guesthouseRequest.intro());
        guesthouse.setAverageRating(guesthouseRequest.averageRating());
        guesthouse.setEmployer(employer); // 연관된 Employer 설정
        //이미지랑 해시태크 추가 구현은 따로 해야함

        // Guesthouse 저장
        return guesthouseRepository.save(guesthouse);
    }
}
