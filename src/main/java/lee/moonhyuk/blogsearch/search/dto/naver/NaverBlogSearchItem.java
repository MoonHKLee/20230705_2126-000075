package lee.moonhyuk.blogsearch.search.dto.naver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverBlogSearchItem {

    private static final String REMOVE_HTML_TAG_PATTERN = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;

    public NaverBlogSearchItem() {
    }

    public NaverBlogSearchItem(String title, String description, String link, String bloggername) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.bloggername = bloggername;
    }
    public String getDescription() {
        return description.replaceAll(REMOVE_HTML_TAG_PATTERN, "");
    }

}
