package zerobase.bud.news.service;

import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import zerobase.bud.news.domain.News;
import zerobase.bud.news.dto.NewsDto;
import zerobase.bud.news.dto.SearchAllNews;
import zerobase.bud.news.repository.BookMarkRepositoryQuerydsl;
import zerobase.bud.news.repository.NewsRepositoryQuerydsl;
import zerobase.bud.news.repository.NewsRepository;
import zerobase.bud.news.repository.BookMarkRepository;
import zerobase.bud.news.util.JwtDecoder;

import java.awt.print.Book;


@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsRepositoryQuerydsl newsRepositoryQuerydsl;
    private final BookMarkRepository bookMarkRepository;
    private final BookMarkRepositoryQuerydsl bookMarkRepositoryQuerydsl;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Transactional
    public NewsDto getNewsDetail(long id, String jwtToken) {
        News news = getNews(id);
        long userId = getCurrentUserId(jwtToken);
        boolean isBookmarked = bookMarkRepository.findByNewsIdAndUserId(id, userId).isPresent();
        news.setHitCount(news.getHitCount() + 1);
        newsRepository.save(news);

        return NewsDto.fromEntity(news, isBookmarked);
    }

    @Transactional(readOnly = true)
    public Page<SearchAllNews.Response> getNewsList(SearchAllNews.Request request) {

        System.out.println("get news list"+SearchAllNews.Response.fromEntitesPage(
                newsRepositoryQuerydsl.findAll(
                        PageRequest.of(request.getPage(), request.getSize()),
                        request.getKeyword(),
                        request.getSort(),
                        request.getOrder(),
                        request.getStartLocalDateTime(),
                        request.getEndLocalDateTime())
        ));
        return SearchAllNews.Response.fromEntitesPage(
                newsRepositoryQuerydsl.findAll(
                        PageRequest.of(request.getPage(), request.getSize()),
                        request.getKeyword(),
                        request.getSort(),
                        request.getOrder(),
                        request.getStartLocalDateTime(),
                        request.getEndLocalDateTime())
        );
    }

    private News getNews(long id) {
        if (id <= 0) {
           // throw new BudException(NEWS_ID_NOT_EXCEED_MIN_VALUE);
        }

        return newsRepository.findById(id)
               .orElseThrow();
    }

    private long getCurrentUserId(String jwtToken) {
        String token = jwtToken.replaceAll("Bearer ", "");
        JwtDecoder jwtDecoder = new JwtDecoder();
        Long decodedUserId = jwtDecoder.decodeJwtAndGetUserId(token);
        return jwtDecoder.decodeJwtAndGetUserId(token);
    }
}
