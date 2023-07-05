package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.ranking.hit.Hit;
import lee.moonhyuk.blogsearch.search.config.KakaoProperties;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.kakao.KakaoBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.service.factory.ApiQueryParamFactory;
import lee.moonhyuk.blogsearch.util.ThreadLocalContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Order(1)
public class KakaoBlogSearchService implements BlogSearchService {
    private final RestTemplate restTemplate;
    private final ApiQueryParamFactory kakaoQueryParamFactory;
    private final KakaoProperties kakaoProperties;

    @Override
    @Hit
    public BlogSearchResponse search(BlogSearchRequest request) {
        ThreadLocalContext.threadLocal.set(request.getQuery());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoProperties.getApiKey());
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        KakaoBlogSearchResponse body = restTemplate.exchange(
                        kakaoQueryParamFactory.getApiUrlWithQueryParam(request),
                        HttpMethod.GET,
                        requestEntity,
                        KakaoBlogSearchResponse.class)
                .getBody();
        return BlogSearchResponse.of(body, request);
    }
}
