package zerobase.bud.news.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.bud.news.domain.BookMark;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark,Long> {

    @Override
    List<BookMark> findAll();

    List<BookMark> findByUserId(Long userId);

    Optional<BookMark> findByNewsIdAndUserId(Long newsId, Long userId);

}
