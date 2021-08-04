package com.jwt.study.service;

import com.jwt.study.dto.LoginDto;
import com.jwt.study.dto.TokenDto;
import com.jwt.study.entity.User;
import com.jwt.study.jwt.JwtFilter;
import com.jwt.study.jwt.TokenProvider;
import com.jwt.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    public TokenDto login(LoginDto loginDto){

        // 1. Login ID/PW를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // 2. 실제로 검증이 이루어지는 부분
        // authenticate 메소드가 호출될 때 CustomUserDetailsService.loadUserByusername이 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto token = tokenProvider.createToken(authentication);

        // 4. RefreshToken 저장

        return token;
    }
}
