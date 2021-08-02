package com.jwt.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor // 기본생성자 자동 추가
@ToString
@Entity
@Table(name = "user")
public class User {

    @JsonIgnore
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty
    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @NotEmpty
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @JsonIgnore
    @NotEmpty
    @Column(name = "tel_no", length = 50, nullable = false)
    private String telNo;

    @Column(name = "push_token", length = 255)
    private String pushToken;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<Vhcle> vhcles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<ServiceMemberMapping> serviceMembers = new ArrayList<>();

    @Builder
    public User(String username, String password,String telNo, String pushToken, List<Vhcle> vhcles) {
        this.username = username;
        this.password = password;
        this.telNo = telNo;
        this.pushToken = pushToken;
        this.vhcles = vhcles;
    }
}
