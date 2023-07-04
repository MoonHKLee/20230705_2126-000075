package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.ranking.hit.Hit;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.kakao.KakaoBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.service.factory.ApiQueryParamFactory;
import lee.moonhyuk.blogsearch.util.ThreadLocalContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Order(1)
public class KakaoBlogSearchService implements BlogSearchService {
    private final RestTemplate restTemplate;
    private final ApiQueryParamFactory kakaoQueryParamFactory;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    @Override
    @Hit
    public BlogSearchResponse search(BlogSearchRequest request) {
        ThreadLocalContext.threadLocal.set(request.getQuery());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoRestApiKey);
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
