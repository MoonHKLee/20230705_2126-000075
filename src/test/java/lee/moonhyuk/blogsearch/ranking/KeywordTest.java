package lee.moonhyuk.blogsearch.ranking;

import lee.moonhyuk.blogsearch.ranking.domain.Keyword;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KeywordTest {

    @Test
    void hit_증가_검사() {
        // given
        Keyword keyword = new Keyword("test");

        // when
        Keyword result = keyword.increaseHit();

        // then
        assertThat(result.getHit()).isEqualTo(2L);
    }

    @Test
    void hit_반환_검사() {
        // given
        Keyword keyword = new Keyword("test");

        // when
        Long hit = keyword.getHit();

        // then
        assertThat(hit).isEqualTo(1L);
    }
}