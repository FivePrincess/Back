package com.example.guzip.security;

import com.example.guzip.entity.user.Employer;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//현재 로그인된 사람의 역할 확인 함수
@Aspect
@Component
public class RoleCheckAspect {

    //RequireEmployer 어노테이션이 실행되기 전에 checkEmployerRole 함수 실행
    @Before("@annotation(com.example.guzip.security.annotations.RequireEmployer)")
    public void checkEmployerRole(){
        checkRole("EMPLOYER");
    }

    @Before("@annotation(com.example.guzip.security.annotations.RequireEmployee)")
    public void checkEmployeeRole() {
        checkRole("EMPLOYEE");
    }

    private void checkRole(String requiredRole) {
        // 현재 인증된 사용자의 정보를 가져옴.
        Object principlal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //현재 사용자가 userdetails 타입인지 확인.
        if(principlal instanceof UserDetails userDetails){

            //현재 사용자가 가진 권한 목록 가져오기
            boolean hasRole = userDetails.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals(requiredRole));

            if (!hasRole) {
                throw new SecurityException("해당 API는 " + requiredRole + " 권한이 필요합니다.");
            }
        }else{
            throw new SecurityException("인증된 사용자가 아닙니다.");
        }
    }
}
