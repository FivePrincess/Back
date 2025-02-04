package com.example.guzip.config;

import com.example.guzip.security.JWTAuthenticationFilter;
import com.example.guzip.security.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.guzip.security.CustomUserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity// 🔥 `@PreAuthorize` 活性化
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JWTUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }


    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    //비밀번호를 해시화하여 저장함.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //보안 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((auth) -> auth.disable());

        http
                .formLogin((auth) -> auth.disable());

        http
                .httpBasic((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) ->auth
                        // 인증 필요없는 경로 -> 회원가입 경로, 로그인 경로, Swagger 관련 경로
                        .requestMatchers("/api/auth/signup","/api/auth/login","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/employer/**").hasAuthority("EMPLOYER")  // 🔥 EMPLOYER API 보호
                        .requestMatchers("/api/employee/**").hasAuthority("EMPLOYEE")  // 🔥 EMPLOYEE API 보호
                        .anyRequest().authenticated()); //나머지 경로는 인증이 필요함.

        http
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // ✅ JWT 필터 추가 (BasicAuthenticationFilter보다 앞에 배치)
        http
                .addFilterBefore(new JWTAuthenticationFilter(jwtUtil, customUserDetailsService),
                        BasicAuthenticationFilter.class);
        return http.build();
    }
}
