package com.jwt.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jwt.study.entity.User;
import com.jwt.study.entity.Vhcle;
import com.jwt.study.util.AES256;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString
public class UserDto {

    @NotBlank(message = "사용자 아이디는 필수 입력값 입니다.")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    @Size(min =3, max = 100)
    private String password;

    @NotBlank(message = "전화번호는 필수 입력값 입니다.")
    @Size(min =11, max =11, message = "전화번호 11자리를 입력해주세요.")
    private String telNo;

    @Max(value = 2, message = "차량 등록은 최대 2대까지 가능합니다.")
    private List<VhcleDto> vhcles = new ArrayList<>();

    @Builder
    public UserDto(String username,  String password,  String telNo, @Size(max = 2, message = "차량은 최대 2대 까지 등록 가능합니다.")List<VhcleDto> vhcles) {
        this.username = username;
        this.password = password;
        this.telNo = telNo;
        this.vhcles = vhcles;
//    
    }

    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .telNo(AES256.getInstance().aesEncode(telNo))
                .build();
    }

    public List<Vhcle> toVhcles(User user){
        return this.vhcles.stream()
                            .map(v -> v.toVhcle(user))
                            .collect(Collectors.toList());
    }
}
