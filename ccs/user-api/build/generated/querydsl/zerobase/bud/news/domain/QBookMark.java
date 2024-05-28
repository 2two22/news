package zerobase.bud.news.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookMark is a Querydsl query type for BookMark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookMark extends EntityPathBase<BookMark> {

    private static final long serialVersionUID = 1141418103L;

    public static final QBookMark bookMark = new QBookMark("bookMark");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> news_id = createNumber("news_id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> user_id = createNumber("user_id", Long.class);

    public QBookMark(String variable) {
        super(BookMark.class, forVariable(variable));
    }

    public QBookMark(Path<? extends BookMark> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookMark(PathMetadata metadata) {
        super(BookMark.class, metadata);
    }

}

