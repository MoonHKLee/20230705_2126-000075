package lee.moonhyuk.blogsearch.ranking.dto;

import lombok.Getter;

@Getter
public class KeywordResponse {
    private final String keyword;
    private final Long hit;

    public KeywordResponse(String keyword, Long hit) {
        this.keyword = keyword;
        this.hit = hit;
    }
}
