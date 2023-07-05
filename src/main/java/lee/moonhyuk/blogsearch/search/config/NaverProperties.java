package lee.moonhyuk.blogsearch.search.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "naver")
public class NaverProperties {

    private API api;
    private Client client;

    public NaverProperties() {
    }

    public NaverProperties(String url, String clientId, String clientSecret) {
        this.api = new API();
        api.setUrl(url);
        this.client = new Client();
        client.setId(clientId);
        client.setSecret(clientSecret);
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
