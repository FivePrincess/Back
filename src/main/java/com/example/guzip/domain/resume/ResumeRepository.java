package com.example.guzip.domain.resume;

import com.example.guzip.entity.application.Resume;
import com.example.guzip.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    long countByEmployee(Employee employee);

    List<Resume> findTop2ByEmployeeOrderByUpdatedDateDesc(Employee employee);
    List<Resume> findByEmployeeOrderByUpdatedDateDesc(Employee employee);

}
