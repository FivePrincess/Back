package com.example.guzip.domain.likedGuesthouse;

import com.example.guzip.entity.liked.LikedGuestHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikedGuesthouseRepository extends JpaRepository<LikedGuestHouse, Long> {

    void deleteByGuestHouseGuesthouseIdAndEmployeeEmployeeId(Long guesthouse_id, Long userId);

    LikedGuestHouse save(LikedGuestHouse userLikeFacility);
    Optional<LikedGuestHouse> findAllByGuestHouseGuesthouseIdAndEmployeeEmployeeId(Long guesthouse_id, Long userId);
    Optional<LikedGuestHouse> findByEmployeeEmployeeId(Long userId);
}
