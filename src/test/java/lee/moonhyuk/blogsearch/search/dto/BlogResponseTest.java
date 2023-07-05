package lee.moonhyuk.blogsearch.search.dto;

import lee.moonhyuk.blogsearch.search.dto.kakao.Document;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchItem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlogResponseTest {

    @Test
    void of_변환_테스트_카카오() {
        // given
        Document document = new Document();
        document.setTitle("Title");
        document.setContents("Contents");
        document.setUrl("https://example.com/blog");
        document.setBlogname("Blog Name");

        // when
        BlogResponse result = BlogResponse.of(document);

        // then
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getContents()).isEqualTo("Contents");
        assertThat(result.getUrl()).isEqualTo("https://example.com/blog");
        assertThat(result.getBlogName()).isEqualTo("Blog Name");
    }

    @Test
    void of_변환_테스트_네이버() {
        // given
        NaverBlogSearchItem item = new NaverBlogSearchItem();
        item.setTitle("Title");
        item.setDescription("Description");
        item.setLink("https://example.com/blog");
        item.setBloggername("Blogger Name");

        // when
        BlogResponse result = BlogResponse.of(item);

        // then
        assertThat(result.getTitle()).isEqualTo("Title");
        assertThat(result.getContents()).isEqualTo("Description");
        assertThat(result.getUrl()).isEqualTo("https://example.com/blog");
        assertThat(result.getBlogName()).isEqualTo("Blogger Name");
    }
}