package com.hanium.hfrecruit.domain.application;

import com.hanium.hfrecruit.domain.recruit.QRecruit;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ApplicationQueryRepository {

    private final JPAQueryFactory query;

    public List<Application> findActiveByRecruit(Long userNo){
        QApplication qApplication = QApplication.application;
        QRecruit qRecruit = QRecruit.recruit;

        List <Application> fetched = query
                .select(qApplication.application)
                .from(qApplication.application)
                .innerJoin(qRecruit.recruit)
                .on(qApplication.recruit.eq(qRecruit.recruit))
                .where(qApplication.application.user.userNo.eq(userNo),
                        qRecruit.recruit.closedBit.eq(0))
                .fetch();

        return fetched;
    }
}
