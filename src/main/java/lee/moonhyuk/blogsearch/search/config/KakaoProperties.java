package lee.moonhyuk.blogsearch.search.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {
    private API api;

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

