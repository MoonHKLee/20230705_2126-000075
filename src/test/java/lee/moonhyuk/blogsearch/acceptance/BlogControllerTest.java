package lee.moonhyuk.blogsearch.acceptance;

import lee.moonhyuk.blogsearch.search.dto.Sort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BlogControllerTest extends ControllerTest {

    @ParameterizedTest
    @NullAndEmptySource
    void 블로그_검색_기본_빈값_검사(String keyword) throws Exception {
        블로그_검색(keyword)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("query should not be blank"))
                .andDo(print());
    }
    @Test
    void 블로그_검색_기본() throws Exception {
        블로그_검색("hello")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.blogs").isArray())
                .andExpect(jsonPath("$.blogs[*].title").exists())
                .andExpect(jsonPath("$.blogs[*].contents").exists())
                .andExpect(jsonPath("$.blogs[*].url").exists())
                .andExpect(jsonPath("$.blogs[*].blogName").exists())
                .andDo(print());
    }

    @Test
    void 블로그_검색_정렬_이상한_값() throws Exception {
        블로그_검색("hello", 2, 10, "asdf")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("sort should be one of accuracy, recency, sim, date"))
                .andDo(print());
    }

    @Test
    void 블로그_검색_페이지() throws Exception {
        블로그_검색("hello", 2, 10, Sort.ACCURACY)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.page").value(2))
                .andExpect(jsonPath("$.blogs").isArray())
                .andExpect(jsonPath("$.blogs[*].title").exists())
                .andExpect(jsonPath("$.blogs[*].contents").exists())
                .andExpect(jsonPath("$.blogs[*].url").exists())
                .andExpect(jsonPath("$.blogs[*].blogName").exists())
                .andDo(print());
    }

    @Test
    void 블로그_검색_사이즈() throws Exception {
        블로그_검색("hello", 1, 5, Sort.ACCURACY)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.blogs").isArray())
                .andExpect(jsonPath("$.blogs[*].title").exists())
                .andExpect(jsonPath("$.blogs[*].contents").exists())
                .andExpect(jsonPath("$.blogs[*].url").exists())
                .andExpect(jsonPath("$.blogs[*].blogName").exists())
                .andDo(print());
    }

    @Test
    void 블로그_검색_최신순() throws Exception {
        블로그_검색("hello", 1, 10, Sort.RECENCY)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.blogs").isArray())
                .andExpect(jsonPath("$.blogs[*].title").exists())
                .andExpect(jsonPath("$.blogs[*].contents").exists())
                .andExpect(jsonPath("$.blogs[*].url").exists())
                .andExpect(jsonPath("$.blogs[*].blogName").exists())
                .andDo(print());
    }

    @Test
    void 블로그_검색_예외_쿼리_empty() throws Exception {
        블로그_검색("", 1, 10, Sort.ACCURACY)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("query should not be blank"))
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 51})
    void 블로그_검색_예외_page_validation(int page) throws Exception {
        블로그_검색("hello", page, 10, Sort.ACCURACY)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("page should be more than 0, less than 51"))
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 51})
    void 블로그_검색_예외_size_validation(int size) throws Exception {
        블로그_검색("hello", 1, size, Sort.ACCURACY)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("size should be more than 0, less than 51"))
                .andDo(print());
    }
}
