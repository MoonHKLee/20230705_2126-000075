package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KakaoQueryParamFactoryTest {

    @Test
    void KAKAO_API_URL_변환_정상_동작() {
        // given
        KakaoQueryParamFactory factory = new KakaoQueryParamFactory();
        BlogSearchRequest request = new BlogSearchRequest("query", "recency", 2, 20);

        // when
        String apiUrl = factory.getApiUrlWithQueryParam(request);

        // then
        assertThat(apiUrl).isEqualTo("https://dapi.kakao.com/v2/search/blog?query=query&sort=recency&page=2&size=20");
    }
}