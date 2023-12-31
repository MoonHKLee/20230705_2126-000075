package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.ranking.hit.Hit;
import lee.moonhyuk.blogsearch.search.config.NaverProperties;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.service.factory.ApiQueryParamFactory;
import lee.moonhyuk.blogsearch.util.ThreadLocalContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Order(2)
public class NaverBlogSearchService implements BlogSearchService{
    private final RestTemplate restTemplate;
    private final ApiQueryParamFactory naverQueryParamFactory;
    private final NaverProperties naverProperties;

    @Override
    @Hit
    public BlogSearchResponse search(BlogSearchRequest request) {
        ThreadLocalContext.threadLocal.set(request.getQuery());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", naverProperties.getClientId());
        headers.add("X-Naver-Client-Secret", naverProperties.getClientSecret());
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        NaverBlogSearchResponse body = restTemplate.exchange(
                        naverQueryParamFactory.getApiUrlWithQueryParam(request),
                        HttpMethod.GET,
                        requestEntity,
                        NaverBlogSearchResponse.class)
                .getBody();
        return BlogSearchResponse.of(body, request);
    }
}
