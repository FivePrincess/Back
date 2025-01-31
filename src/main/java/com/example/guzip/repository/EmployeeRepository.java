package com.example.guzip.repository;

import com.example.guzip.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //기본적으로 제공하는 findById 메서드 사용
}
