package lee.moonhyuk.blogsearch.search.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@Getter
@Setter
@ConfigurationProperties(prefix = "naver")
public class NaverProperties {

    private static final String NAVER_API_URL = "https://openapi.naver.com/v1/search/blog";
    private static final String NAVER_CLIENT_ID = "NCM2Bm8v9TmmIWYotQgN";
    private static final String NAVER_CLIENT_SECRET = "2ExI8_rYEE";

    private API api;
    private Client client;

    public NaverProperties() {
    }

    public NaverProperties(String url, String clientId, String clientSecret) {
        this.api = new API();
        api.setUrl(!StringUtils.hasText(url) ? NAVER_API_URL : url);
        this.client = new Client();
        client.setId(!StringUtils.hasText(clientId) ? NAVER_CLIENT_ID : clientId);
        client.setSecret(!StringUtils.hasText(clientSecret) ? NAVER_CLIENT_SECRET : clientSecret);
    }

    @Getter
    @Setter
    public static class API {
        private String url;
    }

    @Getter
    @Setter
    public static class Client {
        private String id;
        private String secret;
    }

    public String getApiUrl() {
        return api.getUrl();
    }

    public String getClientId() {
        return client.getId();
    }

    public String getClientSecret() {
        return client.getSecret();
    }

    public void setApiUrl(String url) {
        api.setUrl(url);
    }

    public void setClientId(String id) {
        client.setId(id);
    }

    public void setClientSecret(String secret) {
        client.setSecret(secret);
    }
}
