package com.example.guzip.service;

import com.example.guzip.dto.GuesthouseRequest;
import com.example.guzip.entity.Employer;
import com.example.guzip.entity.Guesthouse;
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

    public Guesthouse registerGuestHouse(Long employerId, GuesthouseRequest guesthouseRequest) {

        //Employer 조회
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new IllegalArgumentException("employer not found"));

        Guesthouse guesthouse = new Guesthouse();

        guesthouse.setGuesthouseName(guesthouseRequest.guesthouseName());
        guesthouse.setLocation(guesthouseRequest.location());
        guesthouse.setFacilities(guesthouseRequest.facilities());
        guesthouse.setContact(guesthouseRequest.contact());
        guesthouse.setIntro(guesthouseRequest.intro());
        guesthouse.setAverageRating(guesthouseRequest.averageRating());
        guesthouse.setHashtag(guesthouseRequest.hashtag());
        guesthouse.setImage(guesthouseRequest.image());
        guesthouse.setEmployer(employer); // 연관된 Employer 설정

        // Guesthouse 저장
        return guesthouseRepository.save(guesthouse);
    }
}
