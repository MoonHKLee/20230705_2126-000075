package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.config.NaverProperties;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NaverQueryParamFactoryTest {

    @Test
    void NAVER_API_URL_변환_정상_동작() {
        // given
        NaverProperties naverProperties = new NaverProperties();
        naverProperties.setApi(new NaverProperties.API());
        naverProperties.setApiUrl("https://openapi.naver.com/v1/search/blog.json");
        NaverQueryParamFactory factory = new NaverQueryParamFactory(naverProperties);
        BlogSearchRequest request = new BlogSearchRequest("query", "date", 3, 30);

        // when
        String apiUrl = factory.getApiUrlWithQueryParam(request);

        // then
        assertThat(apiUrl).isEqualTo("https://openapi.naver.com/v1/search/blog.json?query=query&display=30&start=3&sort=date");
    }
}