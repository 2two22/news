package zerobase.bud.news.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.bud.news.repository.BookMarkRepository;
import zerobase.bud.news.repository.BookMarkRepositoryQuerydsl;
import zerobase.bud.news.repository.BookMarkRepositoryQuerydslImpl;

@Service
@RequiredArgsConstructor
public class BookMarkService {

    private BookMarkRepository bookMarkRepository;
    private BookMarkRepositoryQuerydslImpl bookMarkRepositoryQuerydslImpl;


    public int getCount(int user_id){
        return bookMarkRepositoryQuerydslImpl.fetchCount(user_id);
    }


}
