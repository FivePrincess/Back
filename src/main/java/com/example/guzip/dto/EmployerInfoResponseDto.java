package com.example.guzip.dto;

import com.example.guzip.entity.user.User;

import java.time.LocalDateTime;

public record EmployerInfoResponseDto(
        Long employerId,
        String businessNumber,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        User user // User 엔티티 직접 포함 (또는 UserDto 사용)
        // GuestHouse, Payment, LikedApplication은 필요에 따라 DTO로 변환하여 포함
) {

    public static EmployerInfoResponseDto from(com.example.guzip.entity.user.Employer employer) { // Entity -> DTO 변환
        return new EmployerInfoResponseDto(
                employer.getEmployerId(),
                employer.getBusinessNumber(),
                employer.getCreatedDate(),
                employer.getUpdatedDate(),
                employer.getUser() // User 엔티티 직접 할당 (또는 UserDto.from(user) 사용)
                // GuestHouse, Payment, LikedApplication은 필요에 따라 DTO로 변환하여 할당
        );
    }


    // GuestHouse 리스트를 GuestHouseDto 리스트로 변환하는 메서드 (필요한 경우)
    /*
    public List<GuestHouseDto> getGuestHouses() {
        if (guestHouses == null) {
            return null;
        }
        return guestHouses.stream()
                .map(GuestHouseDto::from)
                .collect(Collectors.toList());
    }
    */

    // Payment 리스트를 PaymentDto 리스트로 변환하는 메서드 (필요한 경우)
    /*
    public List<PaymentDto> getPayments() {
        if (payments == null) {
            return null;
        }
        return payments.stream()
                .map(PaymentDto::from)
                .collect(Collectors.toList());
    }
    */

    // LikedApplication 리스트를 LikedApplicationDto 리스트로 변환하는 메서드 (필요한 경우)
    /*
    public List<LikedApplicationDto> getLikedApplications() {
        if (likedApplications == null) {
            return null;
        }
        return likedApplications.stream()
                .map(LikedApplicationDto::from)
                .collect(Collectors.toList());
    }
    */
}
