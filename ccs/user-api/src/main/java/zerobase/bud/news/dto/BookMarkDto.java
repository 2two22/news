package zerobase.bud.news.dto;

import lombok.*;
import zerobase.bud.news.domain.BookMark;

import java.awt.print.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookMarkDto {

    private long id;
    //private long news_id;
    //private long user_id;
    private long newsId;
    private long userId;

    /*
    public static BookMarkDto fromEntity(BookMark bookMark){
        return BookMarkDto.builder()
                .id(bookMark.getId())
                .news_id(bookMark.getNews_id())
                .user_id(bookMark.getUser_id())
                .build();
    } */
    public static BookMarkDto fromEntity(BookMark bookMark){
        return BookMarkDto.builder()
                .id(bookMark.getId())
                .newsId(bookMark.getNewsId())
                .userId(bookMark.getUserId())
                .build();
    }


    public BookMark toEntity() {
        return BookMark.builder()
                .id(id)
                .newsId(newsId)
                .userId(userId)
                .build();
    }


}
