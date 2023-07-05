package lee.moonhyuk.blogsearch.search.dto;

import lee.moonhyuk.blogsearch.search.dto.kakao.Document;
import lee.moonhyuk.blogsearch.search.dto.naver.NaverBlogSearchItem;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogResponse {
    private String title;
    private String contents;
    private String url;
    private String blogName;

    public static BlogResponse of(Document document) {
        BlogResponse response = new BlogResponse();
        response.setTitle(document.getTitle());
        response.setContents(document.getContents());
        response.setUrl(document.getUrl());
        response.setBlogName(document.getBlogname());
        return response;
    }

    public static BlogResponse of(NaverBlogSearchItem item) {
        BlogResponse response = new BlogResponse();
        response.setTitle(item.getTitle());
        response.setContents(item.getDescription());
        response.setUrl(item.getLink());
        response.setBlogName(item.getBloggername());
        return response;
    }
}
