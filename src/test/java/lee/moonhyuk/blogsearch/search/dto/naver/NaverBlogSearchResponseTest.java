package lee.moonhyuk.blogsearch.search.dto.naver;

import lee.moonhyuk.blogsearch.search.dto.BlogResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NaverBlogSearchResponseTest {

    @Test
    void convertToBlogs_정상동작() {
        // given
        NaverBlogSearchItem item1 = new NaverBlogSearchItem("Title 1", "Description 1", "https://example.com/blog1", "Blogger 1");
        NaverBlogSearchItem item2 = new NaverBlogSearchItem("Title 2", "Description 2", "https://example.com/blog2", "Blogger 2");

        NaverBlogSearchResponse response = new NaverBlogSearchResponse();
        response.setItems(Arrays.asList(item1, item2));

        // when
        List<BlogResponse> result = response.convertToBlogs();

        // then
        assertThat(result).hasSize(2);

        assertThat(result.get(0).getTitle()).isEqualTo("Title 1");
        assertThat(result.get(0).getContents()).isEqualTo("Description 1");
        assertThat(result.get(0).getUrl()).isEqualTo("https://example.com/blog1");
        assertThat(result.get(0).getBlogName()).isEqualTo("Blogger 1");

        assertThat(result.get(1).getTitle()).isEqualTo("Title 2");
        assertThat(result.get(1).getContents()).isEqualTo("Description 2");
        assertThat(result.get(1).getUrl()).isEqualTo("https://example.com/blog2");
        assertThat(result.get(1).getBlogName()).isEqualTo("Blogger 2");
    }
}