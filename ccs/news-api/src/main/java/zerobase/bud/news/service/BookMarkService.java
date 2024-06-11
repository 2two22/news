package zerobase.bud.news.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.bud.news.domain.BookMark;
import zerobase.bud.news.domain.News;
import zerobase.bud.news.dto.BookMarkDto;
import zerobase.bud.news.dto.NewsDto;
import zerobase.bud.news.repository.BookMarkRepository;
import zerobase.bud.news.repository.BookMarkRepositoryQuerydslImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookMarkService {

    private BookMarkRepository bookMarkRepository;
    private BookMarkRepositoryQuerydslImpl bookMarkRepositoryQuerydslImpl;

    @Autowired
    public BookMarkService(BookMarkRepository bookMarkRepository, BookMarkRepositoryQuerydslImpl bookMarkRepositoryQuerydslImpl) {
        this.bookMarkRepository = bookMarkRepository;
        this.bookMarkRepositoryQuerydslImpl = bookMarkRepositoryQuerydslImpl;
    }

    @Transactional
    public int getBookMark(Long userId) {
        return getCount(userId);
    }

    @Transactional
    public int getCount(Long userId){ return bookMarkRepositoryQuerydslImpl.fetchCount(userId);}
    //public int getCount(Long user_id){return bookMarkRepositoryQuerydslImpl.fetchCount(user_id);}

    /*
    @Transactional
    public BookMarkDto saveBookMark(Long userId, Long newsId) {

        BookMarkDto bookMarkDto = BookMarkDto.builder()
                .news_id(newsId)
                .user_id(userId)
                .build();
        BookMark bookMark = bookMarkDto.toEntity();
        bookMark = bookMarkRepository.save(bookMark);
        return BookMarkDto.fromEntity(bookMark);
    } */

    @Transactional
    public BookMarkDto saveBookMark(Long userId, Long newsId) {
        Optional<BookMark> optionalBookMark = bookMarkRepository.findByNewsIdAndUserId(newsId, userId);
        if(optionalBookMark.isPresent()){
            // 이미 북마크 한 뉴스라면 삭제 -> 더블 클릭시 삭제되게 구현하기 위함
            bookMarkRepository.delete(optionalBookMark.get());
            return BookMarkDto.fromEntity(optionalBookMark.get());
        }

        BookMarkDto bookMarkDto = BookMarkDto.builder()
                .newsId(newsId)
                .userId(userId)
                .build();

        BookMark bookMark = bookMarkDto.toEntity();
        bookMark = bookMarkRepository.save(bookMark);
        return BookMarkDto.fromEntity(bookMark);
    }

    @Transactional
    // 북마크 된 뉴스 가져오기
    public List<NewsDto> getBookmarkedNews(Long userId) {
        List<BookMark> bookmarks = bookMarkRepository.findByUserId(userId);
        return bookmarks.stream()
                .map(bookmark -> {
                    News news = bookmark.getNews();
                    boolean isBookmarked = true;
                    return NewsDto.fromEntity(news, isBookmarked);
                })
                .collect(Collectors.toList());
    }
}
