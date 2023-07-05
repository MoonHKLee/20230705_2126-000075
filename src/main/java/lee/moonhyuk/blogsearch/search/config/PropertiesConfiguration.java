package lee.moonhyuk.blogsearch.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

    @Value("${kakao.api.url}")
    private String kakaoApiUrl;
    @Value("${kakao.api.key}")
    private String kakaoApiKey;
    @Value("${naver.api.url}")
    private String naverApiUrl;
    @Value("${naver.client.id}")
    private String naverClientId;
    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @ConditionalOnProperty(name = {"kakao.api.key","kakao.api.url"}, matchIfMissing = true)
    @Bean
    public KakaoProperties kakaoProperties() {
        return new KakaoProperties(kakaoApiKey, kakaoApiUrl);
    }

    @ConditionalOnProperty(name = {"naver.api.url","naver.client.id","naver.client.secret"}, matchIfMissing = true)
    @Bean
    public NaverProperties naverProperties() {
        return new NaverProperties(naverApiUrl, naverClientId, naverClientSecret);
    }
}
