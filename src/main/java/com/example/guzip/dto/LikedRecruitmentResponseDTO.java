package com.example.guzip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LikedRecruitmentResponseDTO {
    private Long employeeId;
    private Long likedRecruitmentCount;
    private List<RecruitmentLikedItemDTO> likedItems;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RecruitmentLikedItemDTO{
        private Long likedRecruitmentId;
        private String created_at;
        private String guesthouse_name;
        private String work_location;
        private String contact;
    }

}
