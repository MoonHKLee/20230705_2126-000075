package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchItem;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.service.factory.ApiQueryParamFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NaverBlogSearchServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiQueryParamFactory naverQueryParamFactory;

    private NaverBlogSearchService naverBlogSearchService;

    @BeforeEach
    void setUp() {
        naverBlogSearchService = new NaverBlogSearchService(restTemplate, naverQueryParamFactory);
    }

    @Test
    void search_네이버블로그_서비스_정상동작() {
        // given
        String apiUrl = "https://openapi.naver.com/v1/search/blog.json?query=query&display=10&start=1&sort=date";
        BlogSearchRequest request = new BlogSearchRequest("query", null, 1, 10);
        NaverBlogSearchResponse responseBody = new NaverBlogSearchResponse();
        responseBody.setTotal(50);
        responseBody.setItems(Arrays.asList(
                new NaverBlogSearchItem("Title1", "Description1", "https://example.com/blog1", "Blogger Name1"),
                new NaverBlogSearchItem("Title2", "Description2", "https://example.com/blog2", "Blogger Name2"),
                new NaverBlogSearchItem("Title3", "Description3", "https://example.com/blog3", "Blogger Name3")
        ));
        when(naverQueryParamFactory.getApiUrlWithQueryParam(request)).thenReturn(apiUrl);
        when(restTemplate.exchange(eq(apiUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(NaverBlogSearchResponse.class)))
                .thenReturn(ResponseEntity.ok(responseBody));

        // when
        BlogSearchResponse result = naverBlogSearchService.search(request);

        // then
        assertThat(result.getBlogs()).hasSize(3);
        assertThat(result.getTotal()).isEqualTo(50);
        assertThat(result.getPage()).isEqualTo(1);
        assertThat(result.getSize()).isEqualTo(10);
    }
}