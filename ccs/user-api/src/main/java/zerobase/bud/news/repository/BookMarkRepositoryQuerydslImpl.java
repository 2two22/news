package zerobase.bud.news.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import zerobase.bud.news.domain.QBookMark;

import java.time.LocalDateTime;

import static zerobase.bud.news.domain.QBookMark.bookMark;
import static zerobase.bud.news.domain.QNews.news;

@Repository
@RequiredArgsConstructor
public class BookMarkRepositoryQuerydslImpl implements BookMarkRepositoryQuerydsl {
    private final JPAQueryFactory jpaQueryFactory;
    /*
    public int fetchCount(long user_id) {
        return (int)jpaQueryFactory
                .select(bookMark.count())
                .from(bookMark)
                .where(bookMark.user_id.eq(user_id))
                .fetchCount();
    } */
    public int fetchCount(long user_id) {
        return (int)jpaQueryFactory
                .select(bookMark.count())
                .from(bookMark)
                .where(bookMark.userId.eq(user_id))
                .fetchCount();
    }


}
