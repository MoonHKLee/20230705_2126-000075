package lee.moonhyuk.blogsearch.config;

import lee.moonhyuk.blogsearch.search.service.factory.KakaoQueryParamFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoConfig {

    @Bean
    public KakaoQueryParamFactory kakaoQueryParamFactory() {
        return new KakaoQueryParamFactory();
    }
}
