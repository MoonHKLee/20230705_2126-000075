package lee.moonhyuk.blogsearch.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("serviceEnd")
class BlogControllerNoServiceTest extends ControllerTest {
    @Test
    void 서비스_전체_장애시_목록_예외_처리() throws Exception {
        블로그_검색("naver")
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("end of service list"))
                .andDo(print());
    }
}

