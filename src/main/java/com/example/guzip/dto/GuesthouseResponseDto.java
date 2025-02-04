package com.example.guzip.dto;

import com.example.guzip.entity.guesthouse.GuestHouse;
import com.example.guzip.entity.liked.LikedGuesthouse;
import com.example.guzip.service.GuesthouseServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record GuesthouseResponseDto (
     Long id,
     String name,
     String address,
     Double average_rating,
     int rating_count,
     int liked
){

}
