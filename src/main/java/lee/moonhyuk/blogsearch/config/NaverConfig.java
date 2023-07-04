package lee.moonhyuk.blogsearch.config;

import lee.moonhyuk.blogsearch.search.service.factory.NaverQueryParamFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NaverConfig {
    @Bean
    public NaverQueryParamFactory naverQueryParamFactory() {
        return new NaverQueryParamFactory();
    }
}
