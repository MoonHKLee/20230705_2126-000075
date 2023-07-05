package lee.moonhyuk.blogsearch.search.dto.naver;

import lee.moonhyuk.blogsearch.search.dto.BlogResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class NaverBlogSearchResponse {
    private String title;
    private String link;
    private String description;
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverBlogSearchItem> items;

    public List<BlogResponse> convertToBlogs() {
        return items.stream()
                .map(BlogResponse::of)
                .collect(Collectors.toList());
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setItems(List<NaverBlogSearchItem> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public List<NaverBlogSearchItem> getItems() {
        return items;
    }
}
