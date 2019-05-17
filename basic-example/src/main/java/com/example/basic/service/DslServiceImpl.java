package com.example.basic.service;

import com.example.basic.model.DslModel;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.basic.model.QDslModel.dslModel;


@Service
@RequiredArgsConstructor
public class DslServiceImpl implements DslService {

    private final JPAQueryFactory queryFactory;


    // 전체 호출 쿼리
    @Override
    public List<DslModel> findAll() {
        return queryFactory
                .selectFrom(dslModel)
                .fetch();
    }

    // 전체 페이징 처리 쿼리
    @Override
    public Page<DslModel> getAllPageTable(Pageable pageable) {

        QueryResults<DslModel> result = queryFactory
                .selectFrom(dslModel)
                .offset(pageable.getOffset()) // offset과
                .limit(pageable.getPageSize()) // Limit 을 지정할 수 있고
                //.orderBy(user.userNo.desc()) // 정렬도 가능하다
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}