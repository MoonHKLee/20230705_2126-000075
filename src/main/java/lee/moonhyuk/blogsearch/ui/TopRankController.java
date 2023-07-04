package lee.moonhyuk.blogsearch.ui;

import lee.moonhyuk.blogsearch.ranking.TopRank;
import lee.moonhyuk.blogsearch.ranking.dto.RankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TopRankController {
    private final TopRank topRank;


    @GetMapping("/ranking")
    public ResponseEntity<RankingResponse> ranking() {
        return ResponseEntity.ok(new RankingResponse(topRank.topNEntries(10)));
    }
}
