package com.hanium.hfrecruit.domain.spec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Spec {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long specId;

    @Column(nullable = false)
    private String spceName;

    @Column(nullable = false)
    private String institution;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "spec")
    private List<PersonalSpec> personalSpecs;

    @Builder
    public Spec(String specName, String institution ) {
        this.spceName = specName;
        this.institution = institution;
    }
}
