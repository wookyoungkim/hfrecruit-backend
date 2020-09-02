package com.hanium.hfrecruit.domain.company;


import com.hanium.hfrecruit.domain.Recruit.Recruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CompanyInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long companyNo;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyEmail;

    @Column(nullable = false)
    private Integer managerId;

    @Column(nullable = false)
    private String companyLogo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<CompanyUser> companyUsersList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<Recruit> recruits;
}
