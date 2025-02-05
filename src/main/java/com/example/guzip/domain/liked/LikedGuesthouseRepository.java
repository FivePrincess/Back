package com.example.guzip.domain.liked;

import com.example.guzip.entity.liked.LikedGuestHouse;
import com.example.guzip.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedGuesthouseRepository extends JpaRepository<LikedGuestHouse, Long> {
    long countByEmployee(Employee employee);

    List<LikedGuestHouse> findTop2ByEmployeeOrderByCreatedDateDesc(Employee employee);
    List<LikedGuestHouse> findByEmployeeOrderByCreatedDateDesc(Employee employee);

    void deleteByGuestHouseGuesthouseIdAndEmployeeEmployeeId(Long guesthouse_id, Long userId);

    LikedGuestHouse save(LikedGuestHouse userLikeFacility);
    Optional<LikedGuestHouse> findAllByGuestHouseGuesthouseIdAndEmployeeEmployeeId(Long guesthouse_id, Long userId);
    Optional<LikedGuestHouse> findByEmployeeEmployeeId(Long userId);
}
