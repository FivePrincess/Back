package com.example.guzip.domain.guesthouse;

import com.example.guzip.entity.guesthouse.GuestHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuesthouseRepository extends JpaRepository<GuestHouse,Long>{

    List<GuestHouse> findAllByOrderByAverageRatingDesc();

    List<GuestHouse> findAllByOrderByRatingsCountDesc();

    GuestHouse findByGuesthouseId(Long guestId);

}
