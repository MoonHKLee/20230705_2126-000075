package lee.moonhyuk.blogsearch.search.dto.kakao;

import lee.moonhyuk.blogsearch.search.dto.BlogResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class KakaoBlogSearchResponse {
    private Meta meta;
    private List<Document> documents;

    public List<BlogResponse> convertToBlogs() {
        return documents.stream()
                .map(BlogResponse::of)
                .collect(Collectors.toList());
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
