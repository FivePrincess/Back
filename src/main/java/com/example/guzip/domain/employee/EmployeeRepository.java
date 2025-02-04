package com.example.guzip.domain.employee;

import com.example.guzip.entity.user.Employee;
import com.example.guzip.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * 특정 User가 Employee(직원)인지 확인
     *
     * @param user 사용자 객체
     * @return Employee 존재 여부
     */
    boolean existsByUser(User user);

    /**
     * 특정 User와 연결된 Employee 정보를 가져오는 메서드
     *
     * @param user 사용자 객체
     * @return Employee 정보(Optional)
     */
    Optional<Employee> findByUser(User user);

    Employee findByUserUserId(Long userId);
}
