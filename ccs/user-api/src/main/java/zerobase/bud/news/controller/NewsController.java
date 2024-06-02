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
import java.util.List;
import java.util.Map;

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

    @PostMapping("/users/bookmark")
    public ResponseEntity<String> saveBookmark(@RequestBody Map<String, Object> request, @RequestHeader("Authorization") String jwtToken) {
        // userId와 newsId를 사용하여 북마크를 저장하는 로직을 구현
        String token = jwtToken.replaceAll("Bearer ", "");
        JwtDecoder jwtDecoder = new JwtDecoder();
        Long decodedUserId = jwtDecoder.decodeJwtAndGetUserId(token);

        // 요청 본문에서 newsId를 가져옴
        Long newsId = Long.parseLong(request.get("newsId").toString());

        bookMarkService.saveBookMark(decodedUserId, newsId);

        return ResponseEntity.ok("Bookmark saved successfully");
    }

    // 유저 북마크 개수 조회
    @GetMapping("/news/bookmark")
    public int getBookMark(@RequestHeader("Authorization") String jwtToken){
        String token = jwtToken.replaceAll("Bearer ", "");
        JwtDecoder jwtDecoder = new JwtDecoder();
        Long decodedUserId = jwtDecoder.decodeJwtAndGetUserId(token);
        return bookMarkService.getBookMark(decodedUserId);

    }

    // 유저가 북마크한 뉴스 조회
    @GetMapping("/users/bookmark/list")
    public ResponseEntity<List<NewsDto>> getBookmarkedNews(@RequestHeader("Authorization") String jwtToken) {
        String token = jwtToken.replaceAll("Bearer ", "");
        JwtDecoder jwtDecoder = new JwtDecoder();
        Long decodedUserId = jwtDecoder.decodeJwtAndGetUserId(token);
        List<NewsDto> bookmarks = bookMarkService.getBookmarkedNews(decodedUserId);
        return ResponseEntity.ok(bookmarks);
    }


}