package com.example.guzip.controller;

import com.example.guzip.dto.GuesthouseResponseDto;
import com.example.guzip.dto.UserInfoResponseDto;
import com.example.guzip.entity.user.User;
import com.example.guzip.service.GuesthouseSerivce;
import com.example.guzip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainPageController {

    @Autowired
    GuesthouseSerivce guesthouseSerivce;
    @Autowired
    UserService userService;

    @GetMapping("/guesthouse/all")
    public ResponseEntity<List<GuesthouseResponseDto>> getAllGuesthouse(@PathVariable Long user_id) {
        List<GuesthouseResponseDto> allGuesthouseList = guesthouseSerivce.getAllGuesthouses(user_id);
        return ResponseEntity.ok(allGuesthouseList);
    }

    @GetMapping("/guesthouse/list-by-recommend")
    public ResponseEntity<List<GuesthouseResponseDto>> getGuesthouseByRecommend(@PathVariable Long user_id) {
        List<GuesthouseResponseDto> allGuesthouseList = guesthouseSerivce.getGuesthouseByRatingCount(user_id);
        return ResponseEntity.ok(allGuesthouseList);
    }

    @GetMapping("/guesthouse/list-by-liked")
    public ResponseEntity<List<GuesthouseResponseDto>> getGuesthouseByLiked(@PathVariable Long user_id) {
        List<GuesthouseResponseDto> allGuesthouseList = guesthouseSerivce.getGuesthouseByAverageRating(user_id);
        return ResponseEntity.ok(allGuesthouseList);
    }

    @PutMapping("guesthouse/like/{guesthouse_id}")
    public ResponseEntity<?> likeGuesthouse(@PathVariable Long guesthouse_id, Long user_id) {
        if (userId != null && guesthouse_id != null) {
            return ResponseEntity.ok(guesthouseSerivce.likeGuesthouse(guesthouse_id, user_id));
        }
        return ResponseEntity.notFound().build();
    }

}
