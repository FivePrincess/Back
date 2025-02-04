package com.example.guzip.repository;

import com.example.guzip.entity.liked.LikedGuesthouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedGuesthouseRepository extends JpaRepository<LikedGuesthouse, Long> {

    void deleteByGuestHouseGuesthouseIdAndEmployeeEmployeeId(Long guesthouse_id, Long userId);

    LikedGuesthouse save(LikedGuesthouse userLikeFacility);
    Optional<LikedGuesthouse> findAllByGuestHouseGuesthouseIdAndEmployeeEmployeeId(Long guesthouse_id, Long userId);
    Optional<LikedGuesthouse> findByEmployeeEmployeeId(Long userId);
}
