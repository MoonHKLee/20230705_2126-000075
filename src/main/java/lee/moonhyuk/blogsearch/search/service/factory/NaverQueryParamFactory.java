package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.config.NaverProperties;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverQueryParamFactory implements ApiQueryParamFactory{
    private final NaverProperties naverProperties;
    @Override
    public String getApiUrlWithQueryParam(BlogSearchRequest request) {
        return naverProperties.getApiUrl()
                + "?query=" + request.getQuery()
                + "&display=" + request.getSize()
                + "&start=" + request.getPage()
                + "&sort=" + getSort(request);
    }
    private String getSort(BlogSearchRequest request) {
        return request.getSort().equals("accuracy") ? "sim" : request.getSort();
    }
}
