package zerobase.bud.news.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class BookMark extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long news_id;

    private long user_id;

    @ManyToOne
    @JoinColumn(name = "news_id", insertable = false, updatable = false)
    private News news;


    public News getNews() {
        return news;
    }

}
