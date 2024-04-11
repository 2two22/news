package zerobase.bud.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.bud.news.domain.BookMark;

import java.util.List;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark,Long> {

    @Override
    List<BookMark> findAll();
}
