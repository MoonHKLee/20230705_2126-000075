package lee.moonhyuk.blogsearch.ranking.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class RankingResponse {
    List<KeywordResponse> ranking;

    public RankingResponse(List<Map.Entry<String,Long>> entries) {
        ranking = new ArrayList<>();
        for (Map.Entry<String, Long> entry : entries) {
            ranking.add(new KeywordResponse(entry.getKey(), entry.getValue()));
        }
    }
}
