package lee.moonhyuk.blogsearch.search.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {
    @ConditionalOnProperty(name = "kakao.api.url", matchIfMissing = true)
    @Bean
    public KakaoProperties kakaoProperties() {
        return new KakaoProperties("76a527d0e4ebf76d3bc6c9a87887f75a", "https://dapi.kakao.com/v2/search/blog");
    }

    @ConditionalOnProperty(name = "naver.api.url", matchIfMissing = true)
    @Bean
    public NaverProperties naverProperties() {
        return new NaverProperties("https://openapi.naver.com/v1/search/blog.json", "NCM2Bm8v9TmmIWYotQgN", "2ExI8_rYEE");
    }
}
