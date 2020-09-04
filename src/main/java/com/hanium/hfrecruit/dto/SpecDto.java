package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.spec.PersonalSpec;
import com.hanium.hfrecruit.domain.spec.Spec;
import lombok.Getter;

import java.util.List;

@Getter
public class SpecDto {
    private Long specId;

    private String specName;

    private String institution;

    private List<PersonalSpec> personalSpecs;

    public SpecDto(Spec entity) {
        this.specId = entity.getSpecId();
        this.specName = entity.getSpecName();
        this.institution = entity.getInstitution();
        this.personalSpecs = entity.getPersonalSpecs();
    }

    public Spec toEntity(){
        return Spec.builder()
                .specId(specId)
                .specName(specName)
                .institution(institution)
                .personalSpecs(personalSpecs)
                .build();

    }
}
