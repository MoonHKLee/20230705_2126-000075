package lee.moonhyuk.blogsearch.search.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@Getter
@Setter
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {
    private static final String KAKAO_API_URL = "https://dapi.kakao.com/v2/search/blog";
    private static final String KAKAO_API_KEY = "76a527d0e4ebf76d3bc6c9a87887f75a";
    private API api;

    public KakaoProperties() {
    }

    public KakaoProperties(String key, String url) {
        this.api = new API();
        api.setKey(!StringUtils.hasText(key) ? KAKAO_API_KEY : key);
        api.setUrl(!StringUtils.hasText(url) ? KAKAO_API_URL : url);
    }

    @Getter
    @Setter
    public static class API {
        private String key;
        private String url;
    }

    public String getApiKey() {
        return api.getKey();
    }

    public String getApiUrl() {
        return api.getUrl();
    }

    public void setApiKey(String key) {
        api.setKey(key);
    }

    public void setApiUrl(String url) {
        api.setUrl(url);
    }
}

