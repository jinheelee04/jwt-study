package com.jwt.study.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @Column(name="authority_name", length=50)
    @Enumerated(EnumType.STRING)
    private AuthorityType authorityName;

}
