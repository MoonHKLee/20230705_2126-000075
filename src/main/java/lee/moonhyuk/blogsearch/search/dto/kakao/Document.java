package lee.moonhyuk.blogsearch.search.dto.kakao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Document {
    public Document() {
    }

    public Document(String title, String contents, String url, String blogname) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
    }

    private String title;
    private String contents;
    private String url;
    private String blogname;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String thumbnail;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String datetime;
}
