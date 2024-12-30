package com.example.guzip.dto;

import java.util.List;

public record GuesthouseRequest(
        String guesthouseName,
        String location,
        String facilities,
        String contact,
        String intro,
        Double averageRating,
        List<String> hashtag,
        String image
) { }
