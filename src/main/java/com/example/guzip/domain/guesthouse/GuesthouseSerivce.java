package com.example.guzip.domain.guesthouse;

import com.example.guzip.dto.GuesthouseRequestDto;
import com.example.guzip.dto.GuesthouseResponseDto;
import com.example.guzip.entity.guesthouse.GuestHouse;
import com.example.guzip.entity.user.Employer;
import com.example.guzip.domain.employer.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GuesthouseSerivce {


    public GuestHouse registerGuestHouse(Long employerId, GuesthouseRequestDto guesthouseRequest);

    public List<GuesthouseResponseDto> getAllGuesthouses(Long user_id);

    public List<GuesthouseResponseDto> getGuesthouseByAverageRating(Long user_id);

    public List<GuesthouseResponseDto> getGuesthouseByRatingCount(Long user_id);

    public String likeGuesthouse (Long guesthouse_id, Long user_id);

}
