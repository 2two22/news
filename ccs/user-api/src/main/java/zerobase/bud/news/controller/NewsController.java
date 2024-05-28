package zerobase.bud.news.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.bud.news.dto.BookMarkDto;
import zerobase.bud.news.dto.NewsDto;
import zerobase.bud.news.dto.SearchAllNews;
import zerobase.bud.news.service.BookMarkService;
import zerobase.bud.news.service.NewsService;
import zerobase.bud.news.util.JwtDecoder;

import javax.validation.Valid;
@RequiredArgsConstructor
@RestController
@Slf4j
public class NewsController {
    private final NewsService newsService;
    private final BookMarkService bookMarkService;


    @GetMapping("/")
    public String home(){
        log.debug("HOME");
        System.out.println("at home");
        return "hi!";
    }

    @GetMapping("/news")
    public ResponseEntity<Page<SearchAllNews.Response>> getNewsList(
            @Valid SearchAllNews.Request params) {
        log.debug("Received request with parameters: {}", params);
        return ResponseEntity.ok(newsService.getNewsList(params));
    }

    @GetMapping("/news/detail/{newsId}")
    public ResponseEntity<NewsDto> getNewsDetail(
            @PathVariable("newsId") long id) {

        return ResponseEntity.ok(newsService.getNewsDetail(id));
    }

    @GetMapping("/news/bookmark/{userId}")
    public int getBookMark(@PathVariable Long userId){
        return bookMarkService.getBookMark(userId);
    }



    @PostMapping("/users/bookmark")
    public ResponseEntity<String> saveBookmark( @RequestParam("newsId") Long newsId,  @RequestHeader("Authorization") String jwtToken) {
        // userId와 newsId를 사용하여 북마크를 저장하는 로직을 구현

        JwtDecoder jwtDecoder = new JwtDecoder();
        Long decodedUserId = jwtDecoder.decodeJwtAndGetUserId(jwtToken);
        bookMarkService.saveBookMark(decodedUserId, newsId);

        return ResponseEntity.ok("Bookmark saved successfully");
    }



}