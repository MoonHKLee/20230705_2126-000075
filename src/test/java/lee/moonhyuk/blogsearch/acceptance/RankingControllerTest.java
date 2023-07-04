package lee.moonhyuk.blogsearch.acceptance;

import lee.moonhyuk.blogsearch.search.dto.Sort;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RankingControllerTest extends ControllerTest {

    @Test
    void 랭킹_조회시_횟수_많은순서대로() throws Exception {
        //given
        블로그_검색_반복("halo",8);
        블로그_검색_반복("world",6);
        블로그_검색_반복("java",5);
        블로그_검색_반복("spring",7);

        //then
        랭킹_검색()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ranking[0].keyword").value("halo"))
                .andExpect(jsonPath("$.ranking[0].hit").value(8))
                .andExpect(jsonPath("$.ranking[1].keyword").value("spring"))
                .andExpect(jsonPath("$.ranking[1].hit").value(7))
                .andExpect(jsonPath("$.ranking[2].keyword").value("world"))
                .andExpect(jsonPath("$.ranking[2].hit").value(6))
                .andExpect(jsonPath("$.ranking[3].keyword").value("java"))
                .andExpect(jsonPath("$.ranking[3].hit").value(5))
                .andDo(print())
                .andReturn();
    }

    private void 블로그_검색_반복(String query, int count) throws Exception {
        for (int i = 0; i < count; i++) {
            블로그_검색(query, 1, 10, Sort.ACCURACY);
        }
    }
}
