package lee.moonhyuk.blogsearch.search.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

    @Value("${kakao.api.url:https://dapi.kakao.com/v2/search/blog}")
    private String kakaoApiUrl;
    @Value("${kakao.api.key:76a527d0e4ebf76d3bc6c9a87887f75a}")
    private String kakaoApiKey;
    @Value("${naver.api.url:https://openapi.naver.com/v1/search/blog.json}")
    private String naverApiUrl;
    @Value("${naver.client.id:NCM2Bm8v9TmmIWYotQgN}")
    private String naverClientId;
    @Value("${naver.client.secret:2ExI8_rYEE}")
    private String naverClientSecret;
    @Bean
    public KakaoProperties kakaoProperties() {
        return new KakaoProperties(kakaoApiKey, kakaoApiUrl);
    }
    @Bean
    public NaverProperties naverProperties() {
        return new NaverProperties(naverApiUrl, naverClientId, naverClientSecret);
    }
}
