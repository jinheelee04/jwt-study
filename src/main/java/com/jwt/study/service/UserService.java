package com.jwt.study.service;

import com.jwt.study.dto.UserDto;
import com.jwt.study.entity.Authority;
import com.jwt.study.entity.User;
import com.jwt.study.entity.Vhcle;
import com.jwt.study.repository.UserRepository;

import com.jwt.study.util.AES256;
import com.jwt.study.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원 가입 
     * @param userDto 회원 가입 정보
     * @return
     */
    @Transactional
    public User signup(UserDto userDto){
        // 1. username이 DB에 존재하는지 확인
        if(userRepository.existsByUsername(userDto.getUsername()) == true)
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");

        // 사용자 정보 생성
        User user = User.builder()
                        .username(userDto.getUsername())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .telNo(AES256.getInstance().aesEncode(userDto.getTelNo()))
                        .vhcles(userDto.getVhcles().stream()
                                .map(vhcle -> vhcle.toVhcle())
                                .collect(Collectors.toList()))
                        .build();
        // 3. DB에 정보 저장
        return userRepository.save(user);
    }

    /**
     * 사용자 권한 가져오기
     * @param username 사용자 아이디
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username){
        // 입력받은 username으로 조회
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    /**
     * 사용자 & 권한 정보 가져오기
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities(){
        // SecurityContext에 저장된 username으로 조회
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

}
