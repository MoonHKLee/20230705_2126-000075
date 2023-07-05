package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.search.config.KakaoProperties;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.kakao.Document;
import lee.moonhyuk.blogsearch.search.dto.kakao.KakaoBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.kakao.Meta;
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
public class KakaoBlogSearchServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiQueryParamFactory kakaoQueryParamFactory;

    @Mock
    private KakaoProperties kakaoProperties;

    private KakaoBlogSearchService kakaoBlogSearchService;

    @BeforeEach
    void setUp() {
        kakaoBlogSearchService = new KakaoBlogSearchService(restTemplate, kakaoQueryParamFactory,kakaoProperties);
    }

    @Test
    void search_카카오블로그_서비스_정상동작() {
        // given
        String apiUrl = "https://dapi.kakao.com/v2/search/blog?query=query&sort=accuracy&page=1&size=10";
        BlogSearchRequest request = new BlogSearchRequest("query", null, 1, 10);
        KakaoBlogSearchResponse responseBody = new KakaoBlogSearchResponse();
        responseBody.setMeta(new Meta());
        responseBody.getMeta().setTotalCount(100);
        responseBody.setDocuments(Arrays.asList(
                new Document("Title1", "Contents1", "https://example.com/blog1", "Blog Name1"),
                new Document("Title2", "Contents2", "https://example.com/blog2", "Blog Name2"),
                new Document("Title3", "Contents3", "https://example.com/blog3", "Blog Name3")
        ));
        when(kakaoQueryParamFactory.getApiUrlWithQueryParam(request)).thenReturn(apiUrl);
        when(restTemplate.exchange(eq(apiUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(KakaoBlogSearchResponse.class)))
                .thenReturn(ResponseEntity.ok(responseBody));

        // when
        BlogSearchResponse result = kakaoBlogSearchService.search(request);

        // then
        assertThat(result.getBlogs()).hasSize(3);
        assertThat(result.getTotal()).isEqualTo(100);
        assertThat(result.getPage()).isEqualTo(1);
        assertThat(result.getSize()).isEqualTo(10);
    }
}