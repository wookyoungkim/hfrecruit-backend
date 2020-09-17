package com.hanium.hfrecruit.domain.company;


import com.hanium.hfrecruit.domain.recruit.Recruit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"companyUsersList", "recruits"})
@Builder
public class CompanyInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long companyNo;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyEmail;

    @Column(nullable = false)
    private String companyPage;

    @Column(nullable = false)
    private Integer managerId;

    @Column(nullable = false)
    private String companyLogo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<CompanyUser> companyUsersList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<Recruit> recruits;

//    @Override
//    public String toString() {
//        return "CompanyInfo{" +
//                "companyNo=" + companyNo +
//                ", companyName='" + companyName +
//                ", companyEmail=" + companyEmail +
//                ", managerId=" + managerId +
//                ", companyLogo=" + companyLogo +
//                '}';
//    }
}
