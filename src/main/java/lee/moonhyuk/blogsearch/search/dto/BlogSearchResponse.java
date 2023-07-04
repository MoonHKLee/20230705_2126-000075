package lee.moonhyuk.blogsearch.search.dto;

import lee.moonhyuk.blogsearch.search.dto.kakao.KakaoBlogSearchResponse;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BlogSearchResponse {
    private List<BlogResponse> blogs;
    private int page;
    private int size;
    private long total;


    public static BlogSearchResponse of(KakaoBlogSearchResponse body, BlogSearchRequest request) {
        BlogSearchResponse response = new BlogSearchResponse();
        response.setBlogs(body.convertToBlogs());
        response.setPage(request.getPage());
        response.setSize(request.getSize());
        response.setTotal(body.getMeta().getTotalCount());
        return response;
    }

    public static BlogSearchResponse of(NaverBlogSearchResponse body, BlogSearchRequest request) {
        BlogSearchResponse response = new BlogSearchResponse();
        response.setBlogs(body.convertToBlogs());
        response.setPage(request.getPage());
        response.setSize(request.getSize());
        response.setTotal(body.getTotal());
        return response;
    }
}
