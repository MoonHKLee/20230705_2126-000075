package lee.moonhyuk.blogsearch.ranking.hit;

import lee.moonhyuk.blogsearch.ranking.domain.Keyword;
import lee.moonhyuk.blogsearch.ranking.domain.KeywordRepository;
import lee.moonhyuk.blogsearch.ranking.domain.TopRank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class HitServiceImpl implements HitService {

    private final KeywordRepository keywordRepository;
    private final TopRank topRank;

    @Transactional
    @Override
    public synchronized void hit(String keyword) {
        if (!StringUtils.hasText(keyword)){
            throw new IllegalArgumentException("keyword must not be empty");
        }
        Optional<Keyword> origin = keywordRepository.findByKeyword(keyword);
        if (origin.isEmpty()) {
            keywordRepository.saveAndFlush(new Keyword(keyword));
            topRank.insert(keyword, 1L);
            log.info("새로운 키워드가 등록되었습니다. keyword: {}", keyword);
            return;
        }
        Keyword save = keywordRepository.saveAndFlush(origin.get().increaseHit());
        topRank.insert(keyword, save.getHit());
        log.info("키워드의 조회수가 증가하였습니다. keyword: {},{}", keyword, save.getHit());
    }
}