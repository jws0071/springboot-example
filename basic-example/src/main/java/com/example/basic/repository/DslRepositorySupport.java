package com.example.basic.repository;

import com.example.basic.model.DslModel;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.basic.model.QDslModel.dslModel;

@Repository
public class DslRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public DslRepositorySupport(JPAQueryFactory queryFactory) {
        super(DslModel.class);
        this.queryFactory = queryFactory;
    }

    // 전체 호출 쿼리

    public List<DslModel> findAllList() {
        return queryFactory
                .selectFrom(dslModel)
                .fetch();
    }

    // 전체 페이징 처리 쿼리
    public Page<DslModel> findAllPage(Pageable pageable) {

        QueryResults<DslModel> result = queryFactory
                .selectFrom(dslModel)
                .offset(pageable.getOffset()) // offset과
                .limit(pageable.getPageSize()) // Limit 을 지정할 수 있고
                //.orderBy(user.userNo.desc()) // 정렬도 가능하다
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

}
