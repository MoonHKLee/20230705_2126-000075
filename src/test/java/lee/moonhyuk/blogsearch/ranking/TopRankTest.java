package lee.moonhyuk.blogsearch.ranking;

import lee.moonhyuk.blogsearch.ranking.domain.TopRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TopRankTest {
    private TopRank topRank;

    @BeforeEach
    void setUp() {
        topRank = new TopRank();
    }

    @Test
    void insert_정상동작() {
        // given
        String key = "key";
        Long value = 10L;

        // when
        topRank.insert(key, value);

        // then
        assertThat(topRank.topNEntries(1)).containsExactly(Map.entry(key, value));
    }

    @Test
    void insert_갱신() {
        // given
        String key = "key";
        Long originalValue = 10L;
        Long updatedValue = 20L;

        // when
        topRank.insert(key, originalValue);
        topRank.insert(key, updatedValue);

        // then
        assertThat(topRank.topNEntries(1)).containsExactly(Map.entry(key, updatedValue));
    }

    @Test
    void topNEntries_빈값_체크() {
        // when
        List<Map.Entry<String, Long>> result = topRank.topNEntries(0);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void topNEntries_빈값_체크_더많은값_요구() {
        // when
        List<Map.Entry<String, Long>> result = topRank.topNEntries(3);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void topNEntries_필요한_만큼만_반환_초과() {
        // given
        topRank.insert("key1", 10L);
        topRank.insert("key2", 20L);
        topRank.insert("key3", 30L);

        // when
        List<Map.Entry<String, Long>> result = topRank.topNEntries(4);

        // then
        assertThat(result).containsExactly(
                Map.entry("key3", 30L),
                Map.entry("key2", 20L),
                Map.entry("key1", 10L)
        );
    }

    @Test
    void topNEntries_필요한_만큼만_반환_부족() {
        // given
        topRank.insert("key1", 10L);
        topRank.insert("key2", 30L);
        topRank.insert("key3", 20L);
        topRank.insert("key4", 40L);

        // when
        List<Map.Entry<String, Long>> result = topRank.topNEntries(2);

        // then
        assertThat(result).containsExactly(
                Map.entry("key4", 40L),
                Map.entry("key2", 30L)
        );
    }

    @Test
    void topNEntries_값_일치시_키로_정렬() {
        // given
        topRank.insert("key1", 20L);
        topRank.insert("key2", 10L);
        topRank.insert("key3", 30L);
        topRank.insert("key4", 20L);

        // when
        List<Map.Entry<String, Long>> result = topRank.topNEntries(3);

        // then
        assertThat(result).containsExactly(
                Map.entry("key3", 30L),
                Map.entry("key1", 20L),
                Map.entry("key4", 20L)
        );
    }
}