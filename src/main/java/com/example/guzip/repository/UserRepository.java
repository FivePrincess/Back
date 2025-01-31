package com.example.guzip.repository;

import com.example.guzip.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 아이디을 기반으로 사용자 검색
     *
     * @param username 사용자 아이디
     * @return 사용자 엔티티(Optional)
     */
    Optional<User> findByUsername(String username);

    /**
     * 아이디(username)가 존재하는지 확인 (회원가입 시 중복 체크)
     *
     * @param username 사용자 아이디
     * @return 존재 여부 (true: 존재함, false: 존재하지 않음)
     */
    Boolean existsByUsername(String username);
}
