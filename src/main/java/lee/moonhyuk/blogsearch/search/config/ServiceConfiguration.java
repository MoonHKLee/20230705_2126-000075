package lee.moonhyuk.blogsearch.search.config;

import lee.moonhyuk.blogsearch.search.service.BlogSearchService;
import lee.moonhyuk.blogsearch.search.service.KakaoBlogSearchService;
import lee.moonhyuk.blogsearch.search.service.NaverBlogSearchService;
import lee.moonhyuk.blogsearch.search.service.factory.KakaoQueryParamFactory;
import lee.moonhyuk.blogsearch.search.service.factory.NaverQueryParamFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {

    @Bean
    @ConditionalOnProperty(name = "service.kakao", havingValue = "ON", matchIfMissing = true)
    public BlogSearchService kakaoBlogSearchService(RestTemplate restTemplate,
                                                    KakaoQueryParamFactory kakaoQueryParamFactory,
                                                    KakaoProperties kakaoProperties) {
        return new KakaoBlogSearchService(restTemplate, kakaoQueryParamFactory, kakaoProperties);
    }

    @Bean
    @ConditionalOnProperty(name = "service.naver", havingValue = "ON", matchIfMissing = true)
    public BlogSearchService naverBlogSearchService(RestTemplate restTemplate,
                                                         NaverQueryParamFactory naverQueryParamFactory,
                                                         NaverProperties naverProperties) {
        return new NaverBlogSearchService(restTemplate, naverQueryParamFactory, naverProperties);
    }
}
