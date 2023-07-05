package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.config.KakaoProperties;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoQueryParamFactory implements ApiQueryParamFactory{
    private final KakaoProperties kakaoProperties;

    @Override
    public String getApiUrlWithQueryParam(BlogSearchRequest request) {
        convertWrongSort(request);
        return kakaoProperties.getApiUrl()
                + "?query=" + request.getQuery()
                + "&sort=" + request.getSort()
                + "&page=" + request.getPage()
                + "&size=" + request.getSize();
    }

    private void convertWrongSort(BlogSearchRequest request) {
        if (request.getSort().equals(Sort.ACCURACY.getNaver())) {
            request.setSort(Sort.ACCURACY.getKakao());
        }
        if (request.getSort().equals(Sort.RECENCY.getNaver())) {
            request.setSort(Sort.RECENCY.getKakao());
        }
    }
}
