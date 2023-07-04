package lee.moonhyuk.blogsearch.ranking.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RankingResponseTest {

    @Test
    void 랭킹_테스트() {
        // Given
        List<Map.Entry<String, Long>> entries = Arrays.asList(
                Map.entry("keyword1", 10L),
                Map.entry("keyword2", 5L),
                Map.entry("keyword3", 3L)
        );

        // When
        RankingResponse rankingResponse = new RankingResponse(entries);

        // Then
        List<KeywordResponse> ranking = rankingResponse.getRanking();
        assertThat(ranking).isNotNull();
        assertThat(ranking).hasSize(3);

        KeywordResponse keywordResponse1 = ranking.get(0);
        assertThat(keywordResponse1.getKeyword()).isEqualTo("keyword1");
        assertThat(keywordResponse1.getHit()).isEqualTo(10L);

        KeywordResponse keywordResponse2 = ranking.get(1);
        assertThat(keywordResponse2.getKeyword()).isEqualTo("keyword2");
        assertThat(keywordResponse2.getHit()).isEqualTo(5L);

        KeywordResponse keywordResponse3 = ranking.get(2);
        assertThat(keywordResponse3.getKeyword()).isEqualTo("keyword3");
        assertThat(keywordResponse3.getHit()).isEqualTo(3L);
    }
}