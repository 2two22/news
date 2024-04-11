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
    private long news_id;
    private int user_id;


    public static BookMarkDto fromEntity(BookMark bookMark){
        return BookMarkDto.builder()
                .id(bookMark.getId())
                .news_id(bookMark.getNews_id())
                .user_id(bookMark.getUser_id())
                .build();
    }
}
