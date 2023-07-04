package lee.moonhyuk.blogsearch.search.dto.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Document {
    public Document() {
    }

    public Document(String title, String contents, String url, String blogName) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogName = blogName;
    }

    private String title;
    private String contents;
    private String url;
    private String blogName;
    private String thumbnail;
    private String datetime;
}
