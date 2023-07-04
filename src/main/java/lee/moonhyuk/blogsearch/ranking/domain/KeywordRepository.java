package lee.moonhyuk.blogsearch.ranking.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    Optional<Keyword> findByKeyword(String keyword);
}
