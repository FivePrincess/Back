package com.example.guzip.controller;

import com.example.guzip.dto.GuesthouseRequest;
import com.example.guzip.entity.Guesthouse;
import com.example.guzip.service.GuesthouseSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employer/mypage/guesthouse")
public class GuesthouseController {

    private GuesthouseSerivce guesthouseSerivce;

    public GuesthouseController(GuesthouseSerivce guesthouseSerivce) {
        this.guesthouseSerivce = guesthouseSerivce;
    }

    @PostMapping("/{employer_id}")
    public ResponseEntity<Guesthouse> registerGuesthouse(@PathVariable("employer_id") Long employer_id, @RequestBody GuesthouseRequest guesthouseRequest) {
        Guesthouse savedGuesthouse = guesthouseSerivce.registerGuestHouse(employer_id,guesthouseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuesthouse); // 201 CREATED 반환
    }

}
