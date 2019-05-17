package com.example.basic.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDslModel is a Querydsl query type for DslModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDslModel extends EntityPathBase<DslModel> {

    private static final long serialVersionUID = -1335973438L;

    public static final QDslModel dslModel = new QDslModel("dslModel");

    public final StringPath address = createString("address");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QDslModel(String variable) {
        super(DslModel.class, forVariable(variable));
    }

    public QDslModel(Path<? extends DslModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDslModel(PathMetadata metadata) {
        super(DslModel.class, metadata);
    }

}

