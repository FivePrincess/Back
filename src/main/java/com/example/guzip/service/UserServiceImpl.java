package com.example.guzip.service;

import com.example.guzip.dto.EmployeeInfoResponseDto;
import com.example.guzip.dto.SignUpRequestDTO;
import com.example.guzip.dto.UserInfoResponseDto;
import com.example.guzip.entity.identifier.UserRole;
import com.example.guzip.entity.user.Employee;
import com.example.guzip.entity.user.Employer;
import com.example.guzip.entity.user.User;
import com.example.guzip.repository.EmployeeRepository;
import com.example.guzip.repository.EmployerRepository;
import com.example.guzip.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployerRepository employerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //비밀번호 해시처리

    @Override
    public void signUp(SignUpRequestDTO signUpRequestDTO) {

        String username = signUpRequestDTO.username();
        String password = signUpRequestDTO.password();
        String confirmPassword = signUpRequestDTO.confirmPassword();
        String role = signUpRequestDTO.role();

        //1. 아이디 중복 체크
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        //2. 비밀번호 일치 확인
        if(!confirmPassword.equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //3. 사용자 저장
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(signUpRequestDTO.email());
        user.setName(signUpRequestDTO.name());
        user.setAge(signUpRequestDTO.age());
        user.setGender(signUpRequestDTO.gender());
        user.setNumber(signUpRequestDTO.number());
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());

        //4. 역할에 따라 Employer 또는 Employee 생성
        if("EMPLOYER".equals(role)){
            user.setRole(UserRole.EMPLOYER);
            userRepository.save(user);

            Employer employer = new Employer();
            employer.setUser(user);
            employer.setBusinessNumber(signUpRequestDTO.businessNumber());
            employer.setCreatedDate(LocalDateTime.now());
            employer.setUpdatedDate(LocalDateTime.now());

            employerRepository.save(employer);
        }
        else if("EMPLOYEE".equals(role)){
            user.setRole(UserRole.EMPLOYEE);
            userRepository.save(user);

            Employee employee = new Employee();
            employee.setUser(user);
            employee.setCreatedDate(LocalDateTime.now());
            employee.setUpdatedDate(LocalDateTime.now());

            employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException("잘못된 역할입니다.");
        }
    }

    @Override
    public UserInfoResponseDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("User not found with ID: " + userId));

        return new UserInfoResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getName(),
                user.getAge(),
                user.getRole(),
                user.getEmail(),
                user.getGender(),
                user.getNumber(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}
