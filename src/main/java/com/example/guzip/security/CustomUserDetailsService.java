package com.example.guzip.security;

import com.example.guzip.entity.user.User;
import com.example.guzip.repository.EmployeeRepository;
import com.example.guzip.repository.EmployerRepository;
import com.example.guzip.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //사용자가 나눠져 있음.
    private final UserRepository userRepository;
    private final EmployerRepository employerRepository;
    private final EmployeeRepository employeeRepository;


    public CustomUserDetailsService(UserRepository userRepository, EmployerRepository employerRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employerRepository = employerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //사용자 DB에서 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다." +username));

        //역할을 EMPLOYEE 와 EMPLOYER 확인하여 역할 설정
        String role;
        if(employerRepository.existsByUser(user)) {
            role = "EMPLOYER";
        }else if (employeeRepository.existsByUser(user)) {
            role = "EMPLOYEE";
        }else{
            throw new UsernameNotFoundException("해당 사용자의 역할을 찾을 수 없습니다.");
        }

        //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
        return new CustomUserDetails(user, role);

    }
}
