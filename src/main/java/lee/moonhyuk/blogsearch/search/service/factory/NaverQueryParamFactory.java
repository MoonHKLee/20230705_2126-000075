package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.config.NaverProperties;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverQueryParamFactory implements ApiQueryParamFactory{
    private final NaverProperties naverProperties;
    @Override
    public String getApiUrlWithQueryParam(BlogSearchRequest request) {
        convertWrongSort(request);
        return naverProperties.getApiUrl()
                + "?query=" + request.getQuery()
                + "&display=" + request.getSize()
                + "&start=" + request.getPage()
                + "&sort=" + request.getSort();
    }
    private void convertWrongSort(BlogSearchRequest request) {
        if (request.getSort().equals(Sort.ACCURACY.getKakao())) {
            request.setSort(Sort.ACCURACY.getNaver());
        }
        if (request.getSort().equals(Sort.RECENCY.getKakao())) {
            request.setSort(Sort.RECENCY.getNaver());
        }
    }
}
