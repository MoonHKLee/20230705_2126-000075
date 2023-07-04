package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;

public class NaverQueryParamFactory implements ApiQueryParamFactory{

    private static final String NAVER_API_URL = "https://openapi.naver.com/v1/search/blog.json";

    @Override
    public String getApiUrlWithQueryParam(BlogSearchRequest request) {

        return NAVER_API_URL
                + "?query=" + request.getQuery()
                + "&display=" + request.getSize()
                + "&start=" + request.getPage()
                + "&sort=" + request.getSort();
    }
}
