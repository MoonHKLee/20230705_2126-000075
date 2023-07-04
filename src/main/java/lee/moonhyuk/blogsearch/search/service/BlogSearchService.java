package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;

public interface BlogSearchService {
    BlogSearchResponse search(BlogSearchRequest request);
}
