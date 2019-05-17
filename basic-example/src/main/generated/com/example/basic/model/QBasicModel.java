package com.example.basic.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasicModel is a Querydsl query type for BasicModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBasicModel extends EntityPathBase<BasicModel> {

    private static final long serialVersionUID = 1217756273L;

    public static final QBasicModel basicModel = new QBasicModel("basicModel");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QBasicModel(String variable) {
        super(BasicModel.class, forVariable(variable));
    }

    public QBasicModel(Path<? extends BasicModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasicModel(PathMetadata metadata) {
        super(BasicModel.class, metadata);
    }

}

