package com.jwt.study.controller;

import com.jwt.study.dto.UserDto;
import com.jwt.study.dto.VhcleDto;
import com.jwt.study.entity.User;
import com.jwt.study.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;


    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{

        //given
        List<VhcleDto> vhcles = new ArrayList<>();
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

        String username = "jlee2";
        String password = "jlee123";
        String telNo = "01012345678";

        UserDto userDto = UserDto.builder()
                        .username(username)
                        .password("jleeee")
                        .telNo(telNo)
                        .vhcles(vhcles)
                        .build();

        String url = "http://localhost:" + port + "/api/signup";

        HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDto);
        //when
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, userDto,  User.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

//        Optional<User> resultUser = userRepository.findById(responseEntity.getBody().getUserId());
//        assertThat(resultUser.get().getUsername()).isEqualTo(username);

    }

}
