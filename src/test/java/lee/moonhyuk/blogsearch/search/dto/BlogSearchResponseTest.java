package lee.moonhyuk.blogsearch.search.dto;

import lee.moonhyuk.blogsearch.search.dto.kakao.Document;
import lee.moonhyuk.blogsearch.search.dto.kakao.KakaoBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.kakao.Meta;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchItem;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BlogSearchResponseTest {

    @Test
    void of_카카오_변환_테스트() {
        // given
        KakaoBlogSearchResponse body = new KakaoBlogSearchResponse();
        body.setMeta(new Meta());
        body.getMeta().setTotalCount(100);
        body.setDocuments(Arrays.asList(
                new Document("Title1", "Contents1", "https://example.com/blog1", "Blog Name1"),
                new Document("Title2", "Contents2", "https://example.com/blog2", "Blog Name2"),
                new Document("Title3", "Contents3", "https://example.com/blog3", "Blog Name3")
        ));

        BlogSearchRequest request = new BlogSearchRequest("example query", "recency", 2, 20);

        // when
        BlogSearchResponse result = BlogSearchResponse.of(body, request);

        // then
        assertThat(result.getBlogs()).hasSize(3);
        assertThat(result.getPage()).isEqualTo(2);
        assertThat(result.getSize()).isEqualTo(20);
        assertThat(result.getTotal()).isEqualTo(100);
    }

    @Test
    void of_네이버_변환_테스트() {
        // given
        NaverBlogSearchResponse body = new NaverBlogSearchResponse();
        body.setTotal(50);
        body.setItems(Arrays.asList(
                new NaverBlogSearchItem("Title1", "Description1", "https://example.com/blog1", "Blogger Name1"),
                new NaverBlogSearchItem("Title2", "Description2", "https://example.com/blog2", "Blogger Name2"),
                new NaverBlogSearchItem("Title3", "Description3", "https://example.com/blog3", "Blogger Name3")
        ));

        BlogSearchRequest request = new BlogSearchRequest("example query", null, 1, 10);

        // when
        BlogSearchResponse result = BlogSearchResponse.of(body, request);

        // then
        assertThat(result.getBlogs()).hasSize(3);
        assertThat(result.getPage()).isEqualTo(1);
        assertThat(result.getSize()).isEqualTo(10);
        assertThat(result.getTotal()).isEqualTo(50);
    }
}