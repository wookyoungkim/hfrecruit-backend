package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.spec.PersonalSpec;
import com.hanium.hfrecruit.domain.spec.Spec;
import com.hanium.hfrecruit.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PersonalSpecDto {

    private Long personalSpecId;
    private String certifiedDate;
    private String authDate;
    private String score;
    private User user;
    private Spec spec;

    public PersonalSpecDto(PersonalSpec entity) {
        this.personalSpecId = entity.getPersonalSpecId();
        this.certifiedDate = entity.getCertifiedDate();
        this.authDate = entity.getAuthDate();
        this.score = entity.getScore();
        this.user = entity.getUser();
        this.spec =entity.getSpec();
    }

    public PersonalSpecDto(Long personalSpecId, String certifiedDate, String authDate, String score) {
        this.personalSpecId = personalSpecId;
        this.certifiedDate = certifiedDate;
        this.authDate = authDate;
        this.score = score;
    }

    public PersonalSpec toEntity() {
        return PersonalSpec.builder()
                .personalSpecId(personalSpecId)
                .score(score)
                .certifiedDate(certifiedDate)
                .authDate(authDate)
                .user(user)
                .spec(spec)
                .build();
    }

}
