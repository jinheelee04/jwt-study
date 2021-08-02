package com.jwt.study.service;

import com.jwt.study.dto.UserDto;
import com.jwt.study.dto.VhcleDto;
import com.jwt.study.entity.User;
import com.jwt.study.entity.Vhcle;
import com.jwt.study.repository.UserRepository;
import com.jwt.study.util.AES256;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{

        //given
        List<VhcleDto>  vhcles = new ArrayList<>();
        VhcleDto vhcle1 = VhcleDto.builder()
                            .frontNmbr("23가")
                            .backNmbr("5555")
                            .vhcleNm("소나타")
                            .build();
        VhcleDto vhcle2 = VhcleDto.builder()
                            .frontNmbr("45나")
                            .backNmbr("4444")
                            .vhcleNm("레이")
                            .build();
        vhcles.add(vhcle1);
        vhcles.add(vhcle2);

        String username = "jlee";
        String encPassword = passwordEncoder.encode("jlee123");
        String encTelNo = AES256.getInstance().aesEncode("01012345678");

        UserDto userDto = UserDto.builder()
                        .username(username)
                        .password(encPassword)
                        .telNo(encTelNo)
                        .vhcles(vhcles)
                        .build();

        //when


        //then


    }

}
