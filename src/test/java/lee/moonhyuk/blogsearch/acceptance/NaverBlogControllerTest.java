package lee.moonhyuk.blogsearch.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("naver")
class NaverBlogControllerTest extends ControllerTest {
    @Test
    void 블로그_검색_카카오_장애시_네이버() throws Exception {
        블로그_검색("hello")
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}

