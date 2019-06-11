package com.example.basic.repository;

import com.example.basic.model.DslModel;
import com.example.basic.model.QDslModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.basic.model.QDslModel.dslModel;

@Repository
public class DslRepositorySupport extends QuerydslRepositorySupport implements DslRepository{
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

    // 검색 및 페이징 처리
    public Page<DslModel> SearchAllPage(String flag_info,String search_info,Pageable pageable) {

        QueryResults<DslModel> result_fin = queryFactory
                .selectFrom(dslModel)
                .where(
                        eqcheck(flag_info,search_info)
                )
                .offset(pageable.getOffset()) // offset과
                .limit(pageable.getPageSize()) // Limit 을 지정할 수 있고

                .fetchResults();
        return new PageImpl<>(result_fin.getResults(), pageable, result_fin.getTotal());
    }

    private BooleanExpression eqcheck(String flag_info, String search_info) {

        if (flag_info.equals("1")) {
            long chang_search_info=Integer.parseInt(search_info);
            System.out.println("x1");
            return dslModel.id.eq(chang_search_info);
        }else if(flag_info.equals("2")){
            System.out.println("x2");
            return dslModel.name.eq(search_info);
        }else if(flag_info.equals("3")){
            System.out.println("x3");
            return dslModel.address.eq(search_info);
        }else{
            System.out.println("x4");
            long chang_search_info=Integer.parseInt(search_info);
            return dslModel.id.eq(chang_search_info).or(dslModel.name.eq(search_info)).or(dslModel.address.eq(search_info));
        }

    }


}
