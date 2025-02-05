package com.example.guzip.domain.liked;

import com.example.guzip.entity.liked.LikedAnnouncment;
import com.example.guzip.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedRecruitmentRepository extends JpaRepository<LikedAnnouncment, Long> {
    long countByEmployee(Employee employee);

    List<LikedAnnouncment> findTop2ByEmployeeOrderByCreatedDateDesc(Employee employee);
    List<LikedAnnouncment> findByEmployeeOrderByCreatedDateDesc(Employee employee);
}
