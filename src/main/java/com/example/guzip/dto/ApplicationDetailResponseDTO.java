package com.example.guzip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationDetailResponseDTO {
    private Long employeeId;
    private Long applicationCount;
    private List<ApplicationItemDTO> applicationItems;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApplicationItemDTO{
        private Long applicationId;
        private String created_at;
        private String guesthouse_name;
        private String title;
        private boolean status;
    }

}
