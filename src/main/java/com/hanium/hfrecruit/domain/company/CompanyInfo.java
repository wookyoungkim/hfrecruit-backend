package com.hanium.hfrecruit.domain.company;


import com.hanium.hfrecruit.domain.recruit.Recruit;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"companyUsersList", "recruits"})
@Builder
@EntityListeners(AuditingEntityListener.class)
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
    private String managerId;

    private Long companyLogo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<CompanyUser> companyUsersList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyInfo")
    private List<Recruit> recruits;

    public void update(String companyName, String companyEmail, String companyPage, String managerId, Long companyLogo) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPage = companyPage;
        this.managerId = managerId;
        this.companyLogo = companyLogo;
    }

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
