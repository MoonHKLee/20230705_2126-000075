package lee.moonhyuk.blogsearch.search.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {
    private static final String KAKAO_API_URL = "https://dapi.kakao.com/v2/search/blog";
    private static final String KAKAO_API_KEY = "76a527d0e4ebf76d3bc6c9a87887f75a";
    private static final String NAVER_API_URL = "https://openapi.naver.com/v1/search/blog.json";
    private static final String NAVER_CLIENT_ID = "NCM2Bm8v9TmmIWYotQgN";
    private static final String NAVER_CLIENT_SECRET = "2ExI8_rYEE";
    @ConditionalOnProperty(name = "kakao.api.url", matchIfMissing = true)
    @Bean
    public KakaoProperties kakaoProperties() {
        return new KakaoProperties(KAKAO_API_KEY, KAKAO_API_URL);
    }

    @ConditionalOnProperty(name = "naver.api.url", matchIfMissing = true)
    @Bean
    public NaverProperties naverProperties() {
        return new NaverProperties(NAVER_API_URL, NAVER_CLIENT_ID, NAVER_CLIENT_SECRET);
    }
}
