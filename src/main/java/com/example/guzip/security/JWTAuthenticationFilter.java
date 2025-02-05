package com.example.guzip.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public JWTAuthenticationFilter(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Authorization 헤더에서 JWT 토큰 추출
        String token = getTokenFromRequest(request);

        // 2. 토큰이 유효한 경우에만 SecurityContext에 사용자 정보 저장
        if (token != null && !jwtUtil.isExpired(token)) {
            String username = jwtUtil.getUsername(token);
            String role = jwtUtil.getRole(token);

            // UserDetailsService 통해서 사용자 정보 조회
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            // 권한 리스트 생성 (Spring Security는 role 앞에 "ROLE_"을 붙이는 것이 권장됨)
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

            // 인증 객체 생성 후 SecurityContext에 설정
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, Collections.singletonList(authority));

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // 필터 체인 계속 진행
        filterChain.doFilter(request, response);
    }

    // 요청에서 Authorization 헤더에서 JWT 토큰 추출 메서드
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // "Bearer " 이후의 순수 토큰 반환
        }

        return null;
    }
}
