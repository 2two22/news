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
        System.out.println("Params!!!!!"+params);
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

    @PostMapping("/news/bookmark")
    public BookMarkDto saveBookMark(@RequestBody BookMarkDto bookMarkDto) {
        return bookMarkService.saveBookMark(bookMarkDto);
    }



}