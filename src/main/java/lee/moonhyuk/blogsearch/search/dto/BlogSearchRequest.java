package lee.moonhyuk.blogsearch.search.dto;

import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class BlogSearchRequest {

    @NotBlank(message = "query should not be blank")
    private String query;

    private final String sort;

    @Min(value = 1, message = "page should be more than 0, less than 51")
    @Max(value = 50, message = "page should be more than 0, less than 51")
    private Integer page;

    @Min(value = 1, message = "size should be more than 0, less than 51")
    @Max(value = 50, message = "size should be more than 0, less than 51")
    private Integer size;

    public BlogSearchRequest(String query, String sort, Integer page, Integer size) {
        query = query.trim();
        if (!StringUtils.hasText(query)) {
            throw new IllegalArgumentException("query should not be blank");
        }
        this.query = query.trim();
        this.sort = sort == null ? "accuracy" : sort;
        this.page = page == null ? 1 : page;
        this.size = size == null ? 10 : size;
    }

    public String getSort() {
        if (this.sort.equals("accuracy")) {
            return Sort.ACCURACY.getKakao();
        }
        if (this.sort.equals("recency")) {
            return Sort.RECENCY.getKakao();
        }
        if (this.sort.equals("sim")) {
            return Sort.ACCURACY.getNaver();
        }
        if (this.sort.equals("date")) {
            return Sort.RECENCY.getNaver();
        }
        return Sort.ACCURACY.getKakao();
    }
}