package com.jwt.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "service_member_mapping")
@Getter
@Setter
public class ServiceMemberMapping {


    @Id @GeneratedValue
    @Column(name="service_member_id")
    private Long serviceMemberId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "authority_name")
    private Authority authority;
}
