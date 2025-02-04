package com.example.guzip.dto;

import com.example.guzip.entity.liked.LikedGuestHouse;

public record GuesthouseResponseDto (
     Long id,
     String name,
     String address,
     Double average_rating,
     int rating_count,
     int liked
){

}
