package com.jwt.study.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "vhcle")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vhcle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vhcle_id")
    private Long vhcleId;

    @ManyToOne(fetch = LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "front_nmbr", length = 5, nullable = false)
    private String frontNmbr;

    @Column(name = "back_nmbr", length = 5, nullable = false)
    private String backNmbr;

    @Column(name = "vhcle_nm", length = 50)
    private String vhcleNm;
    @Builder
    public Vhcle(String frontNmbr, String backNmbr, String vhcleNm, User user) {
        this.frontNmbr = frontNmbr;
        this.backNmbr = backNmbr;
        this.vhcleNm = vhcleNm;
        this.user = user;
    }
}
