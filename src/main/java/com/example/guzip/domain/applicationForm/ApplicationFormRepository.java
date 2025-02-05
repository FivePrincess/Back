package com.example.guzip.domain.applicationForm;

import com.example.guzip.entity.application.ApplicationForm;
import com.example.guzip.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {
    long countByEmployee(Employee employee);

    List<ApplicationForm> findTop2ByEmployeeOrderByUpdatedDateDesc(Employee employee);
    List<ApplicationForm> findByEmployeeOrderByUpdatedDateDesc(Employee employee);
}
