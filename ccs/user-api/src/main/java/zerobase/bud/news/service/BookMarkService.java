package zerobase.bud.news.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.bud.news.client.UserClient;
import zerobase.bud.news.domain.BookMark;
import zerobase.bud.news.dto.BookMarkDto;
import zerobase.bud.news.repository.BookMarkRepository;
import zerobase.bud.news.repository.BookMarkRepositoryQuerydslImpl;

@Service
public class BookMarkService {
    private final UserClient userClient;

    @Autowired
    public BookMarkService(UserClient userClient){

        this.userClient = userClient;
    }


    public int getBookMark(Long userId) {
        int id = userClient.getBookMark(userId);
        return getCount(id);
    }


    private BookMarkRepository bookMarkRepository;
    private BookMarkRepositoryQuerydslImpl bookMarkRepositoryQuerydslImpl;


    public int getCount(int user_id){
       return bookMarkRepositoryQuerydslImpl.fetchCount(user_id);
    }

    @Transactional
    public BookMarkDto saveBookMark(BookMarkDto bookMarkDto) {
        BookMark bookMark = bookMarkDto.toEntity();
        bookMark = bookMarkRepository.save(bookMark);
        return BookMarkDto.fromEntity(bookMark);
    }

}
