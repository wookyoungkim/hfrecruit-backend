package com.hanium.hfrecruit.domain.recruit;

import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recruit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long recruitNo;

    @Column(nullable = false)
    private String recruitTitle;

    @Column(nullable = false)
    private String recruitContent;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String closedDate;

    @Column(nullable = false)
    private Integer closedBit;

    @Column(nullable = false)
    private String question1;

    @Column(nullable = false)
    private String question2;

    @Column(nullable = false)
    private String question3;

    private String question4;

    private String question5;

    @ManyToOne
    @JoinColumn(name = "company_no")
    private CompanyInfo companyInfo;


    @ManyToOne
    @JoinColumn(name = "company_user_id")
    private CompanyUser companyUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruit")
    private List<Application> applicationList;

}
