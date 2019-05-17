package com.example.basic.repository;

import com.example.basic.model.DslModel;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    public List<DslModel> findByName(String name) {
        return queryFactory
                .selectFrom(dslModel)
                .where(dslModel.name.eq(name))
                .fetch();
    }
}
