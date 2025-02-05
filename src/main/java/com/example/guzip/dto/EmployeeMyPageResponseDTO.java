package com.example.guzip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeMyPageResponseDTO {
    private boolean success;
    private Data data;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Data {
        private Profile profile;
        private Count count;
        private List<ResumeDTO> resumes;
        private List<ApplicationDTO> applications;
        private Scrap scraps;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Profile {
        private Long employee_id;
        private String employee_name;
        private String gender;
        private int age;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Count {
        private long resume_count;
        private long application_count;
        private long scrap_recruitment_count;
        private long scrap_guesthouse_count;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ResumeDTO {
        private Long resume_id;
        private String title;
        private String description;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApplicationDTO {
        private Long announcement_id;
        private String created_at;
        private String guesthouse_name;
        private String title;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Scrap {
        private List<ScrapGuesthouse> guesthouse;
        private List<ScrapAnnouncement> announcement;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ScrapGuesthouse {
        private Long guesthouse_id;
        private String scrap_date;
        private String guesthouse_name;
        private String work_location;
        private String contact;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ScrapAnnouncement {
        private Long announcement_id;
        private String scrap_date;
        private String guesthouse_name;
        private String title;
    }
}
