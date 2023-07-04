package lee.moonhyuk.blogsearch.search.dto.kakao;

import lee.moonhyuk.blogsearch.search.dto.BlogResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KakaoBlogSearchResponseTest {

    @Test
    void convertToBlogs_정상동작() {
        // given
        Document document1 = new Document("Title 1", "Contents 1", "https://example.com/blog1", "Blog 1");
        Document document2 = new Document("Title 2", "Contents 2", "https://example.com/blog2", "Blog 2");

        KakaoBlogSearchResponse response = new KakaoBlogSearchResponse();
        response.setDocuments(Arrays.asList(document1, document2));

        // when
        List<BlogResponse> result = response.convertToBlogs();

        // then
        assertThat(result).hasSize(2);

        assertThat(result.get(0).getTitle()).isEqualTo("Title 1");
        assertThat(result.get(0).getContents()).isEqualTo("Contents 1");
        assertThat(result.get(0).getUrl()).isEqualTo("https://example.com/blog1");
        assertThat(result.get(0).getBlogName()).isEqualTo("Blog 1");

        assertThat(result.get(1).getTitle()).isEqualTo("Title 2");
        assertThat(result.get(1).getContents()).isEqualTo("Contents 2");
        assertThat(result.get(1).getUrl()).isEqualTo("https://example.com/blog2");
        assertThat(result.get(1).getBlogName()).isEqualTo("Blog 2");
    }
}