package com.example.guzip.domain.guesthouse;

import com.example.guzip.dto.GuesthouseRequestDto;
import com.example.guzip.entity.guesthouse.GuestHouse;
import com.example.guzip.security.annotations.RequireEmployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employer/mypage/guesthouse")
public class GuesthouseController {

    @Autowired
    private GuesthouseSerivce guesthouseSerivce;

    @RequireEmployer
    @PostMapping("/{employer_id}")
    public ResponseEntity<GuestHouse> registerGuesthouse(@PathVariable("employer_id") Long employer_id, @RequestBody GuesthouseRequestDto guesthouseRequest) {
        GuestHouse savedGuesthouse = guesthouseSerivce.registerGuestHouse(employer_id,guesthouseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuesthouse); // 201 CREATED 반환
    }

}
