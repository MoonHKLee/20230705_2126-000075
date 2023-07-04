package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.ranking.hit.Hit;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.service.factory.ApiQueryParamFactory;
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
@Order(2)
public class NaverBlogSearchService implements BlogSearchService{
    private final RestTemplate restTemplate;
    private final ApiQueryParamFactory naverQueryParamFactory;

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Override
    @Hit
    public BlogSearchResponse search(BlogSearchRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", naverClientId);
        headers.add("X-Naver-Client-Secret", naverClientSecret);
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
