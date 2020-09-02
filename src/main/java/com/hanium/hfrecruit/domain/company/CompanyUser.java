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
public class CompanyUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long companyUserId;

    private Integer companyUserCode;

    @ManyToOne
    @JoinColumn(name = "company_no")
    private CompanyInfo companyInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyUser")
    private List<Recruit> recruits;

}
