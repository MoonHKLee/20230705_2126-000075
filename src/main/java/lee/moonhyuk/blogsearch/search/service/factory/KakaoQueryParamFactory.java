package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;

public class KakaoQueryParamFactory implements ApiQueryParamFactory{

    private static final String KAKAO_API_URL = "https://dapi.kakao.com/v2/search/blog";

    @Override
    public String getApiUrlWithQueryParam(BlogSearchRequest request) {
        return KAKAO_API_URL
                + "?query=" + request.getQuery()
                + "&sort=" + request.getSort()
                + "&page=" + request.getPage()
                + "&size=" + request.getSize();
    }
}
