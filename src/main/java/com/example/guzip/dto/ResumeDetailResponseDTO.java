package com.example.guzip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResumeDetailResponseDTO {
    private Long employeeId;
    private Long resumeCount;
    private List<ResumeItemDTO> resumes;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ResumeItemDTO {
        private Long resumeId;
        private String title;
        private String description;
    }
}
