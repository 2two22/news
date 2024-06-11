package zerobase.bud.news.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNews is a Querydsl query type for News
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNews extends EntityPathBase<News> {

    private static final long serialVersionUID = -1570647436L;

    public static final QNews news = new QNews("news");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath company = createString("company");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> hitCount = createNumber("hitCount", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath journalistNames = createString("journalistNames");

    public final StringPath journalistOriginalNames = createString("journalistOriginalNames");

    public final StringPath keywords = createString("keywords");

    public final StringPath link = createString("link");

    public final StringPath mainImgUrl = createString("mainImgUrl");

    public final DateTimePath<java.time.LocalDateTime> registeredAt = createDateTime("registeredAt", java.time.LocalDateTime.class);

    public final StringPath summaryContent = createString("summaryContent");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QNews(String variable) {
        super(News.class, forVariable(variable));
    }

    public QNews(Path<? extends News> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNews(PathMetadata metadata) {
        super(News.class, metadata);
    }

}

