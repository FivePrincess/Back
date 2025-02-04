package com.example.guzip.domain.employer;

import com.example.guzip.entity.user.Employer;
import com.example.guzip.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {

    /**
     * 특정 User가 Employer(사장)인지 확인
     *
     * @param user 사용자 객체
     * @return Employer 존재 여부
     */
    boolean existsByUser(User user);

    /**
     * 특정 User와 연결된 Employer 정보를 가져오는 메서드
     *
     * @param user 사용자 객체
     * @return Employer 정보(Optional)
     */
    Optional<Employer> findByUser(User user);
}
