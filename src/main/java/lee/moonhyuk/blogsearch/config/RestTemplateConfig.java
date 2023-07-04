package lee.moonhyuk.blogsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public void customizeRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(30)) // 연결 타임아웃 설정
                .setReadTimeout(Duration.ofSeconds(30)); // 읽기 타임아웃 설정
    }
}
