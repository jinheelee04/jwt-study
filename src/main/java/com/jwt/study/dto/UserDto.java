package com.jwt.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jwt.study.entity.User;
import com.jwt.study.entity.Vhcle;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "사용자 아이디는 필수 입력값 입니다.")
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    @Size(min =3, max = 100)
    private String password;

    @NotBlank(message = "전화번호는 필수 입력값 입니다.")
    @Size(min =11, max =11, message = "전화번호 11자리를 입력해주세요.")
    private String telNo;

    @Size(max = 2, message = "차량은 최대 2대 까지 등록 가능합니다.")
    private List<VhcleDto> vhcles = new ArrayList<>();

    @Builder
    public UserDto(String username,  String password,  String telNo, List<VhcleDto> vhcles) {
        this.username = username;
        this.password = password;
        this.telNo = telNo;
        this.vhcles = vhcles;
    }

}
