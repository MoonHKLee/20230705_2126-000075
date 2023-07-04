package lee.moonhyuk.blogsearch.search.service.factory;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;

public interface ApiQueryParamFactory {
    String getApiUrlWithQueryParam(BlogSearchRequest request);
}
