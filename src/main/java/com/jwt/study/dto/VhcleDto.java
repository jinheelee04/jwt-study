package com.jwt.study.dto;

import com.jwt.study.entity.User;
import com.jwt.study.entity.Vhcle;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class VhcleDto {

    @NotBlank
    @Size(min =2, max = 5)
    private String frontNmbr;

    @NotBlank
    @Size(min =4, max = 5)
    private String backNmbr;

    @Size(min =2, max = 50)
    private String vhcleNm;

    @Builder
    public VhcleDto(String frontNmbr, String backNmbr, String vhcleNm) {
        this.frontNmbr = frontNmbr;
        this.backNmbr = backNmbr;
        this.vhcleNm = vhcleNm;
    }

    public Vhcle toVhcle(User user){
        return Vhcle.builder()
                    .frontNmbr(frontNmbr)
                    .backNmbr(backNmbr)
                    .vhcleNm(vhcleNm)
                    .user(user)
                    .build();

    }
}
