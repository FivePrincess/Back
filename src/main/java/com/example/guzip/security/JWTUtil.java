package com.example.guzip.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {

        //객체 변수로 암호화 하기 위한 메소드 구현
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    //JWT 검증 시 실행 -> username 추출
    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }
    //JWT 검증 시 실행 -> role 추출 (EMPLOYER or EMPLOYEE)
    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    //JWT 검증 시 실행 -> JWT가 만료되었는지 확인
    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }
    //JWT 토큰 생성
    public String createJWT(String username, String role, Long expiredMs) {

        //토큰에 username, role 저장
        return Jwts.builder()
                .claim("username",username)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))  //현재시간 기준으로 발급 시간 설정
                .expiration(new Date(System.currentTimeMillis()+expiredMs))
                .signWith(secretKey) //서명을 추가하여 변조 방지
                .compact(); //JWT 토큰을 문자열로 반환

    }
}
