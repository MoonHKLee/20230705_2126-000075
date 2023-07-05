package lee.moonhyuk.blogsearch.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("naver")
class NaverBlogControllerTest extends ControllerTest {
    @Test
    void 블로그_검색_카카오_장애시_네이버() throws Exception {
        블로그_검색("naver")
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
}

