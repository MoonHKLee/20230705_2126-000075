package lee.moonhyuk.blogsearch.acceptance;

import lee.moonhyuk.blogsearch.search.dto.Sort;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    protected ResultActions 블로그_검색(String query, int page, int size, Sort sort) throws Exception {
        return mockMvc.perform(get("/blogs")
                .param("query", query)
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sort", sort.getKakao())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
    }

    protected ResultActions 블로그_검색(String query) throws Exception {
        return mockMvc.perform(get("/blogs")
                .param("query", query)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
    }

    protected ResultActions 랭킹_검색() throws Exception {
        return mockMvc.perform(get("/ranking")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
    }
}
