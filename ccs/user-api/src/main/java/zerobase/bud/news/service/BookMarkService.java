package zerobase.bud.news.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.bud.news.domain.BookMark;
import zerobase.bud.news.dto.BookMarkDto;
import zerobase.bud.news.dto.NewsDto;
import zerobase.bud.news.repository.BookMarkRepository;
import zerobase.bud.news.repository.BookMarkRepositoryQuerydslImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMarkService {


    public int getBookMark(Long userId) {
        return getCount(userId);
    }


    private BookMarkRepository bookMarkRepository;
    private BookMarkRepositoryQuerydslImpl bookMarkRepositoryQuerydslImpl;


    public int getCount(Long user_id){
       return bookMarkRepositoryQuerydslImpl.fetchCount(user_id);
    }


    @Transactional
    public BookMarkDto saveBookMark(Long userId, Long newsId) {

        BookMarkDto bookMarkDto = BookMarkDto.builder()
                .news_id(newsId)
                .user_id(userId)
                .build();
        BookMark bookMark = bookMarkDto.toEntity();
        bookMark = bookMarkRepository.save(bookMark);
        return BookMarkDto.fromEntity(bookMark);
    }

    // 북마크 된 뉴스 가져오기
    public List<NewsDto> getBookmarkedNews(Long userId) {
        List<BookMark> bookmarks = bookMarkRepository.findByUserId(userId);
        return bookmarks.stream()
                .map(bookmark -> NewsDto.fromEntity(bookmark.getNews()))
                .collect(Collectors.toList());
    }
}
