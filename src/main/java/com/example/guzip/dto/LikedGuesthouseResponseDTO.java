package com.example.guzip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LikedGuesthouseResponseDTO {
    private Long employeeId;
    private Long likedGuestCount;
    private List<GuesthouseLikedItemDTO> likedItems;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GuesthouseLikedItemDTO{
        private Long likedGuestId;
        private String created_at;
        private String guesthouse_name;
        private String work_location;
        private String contact;
    }

}