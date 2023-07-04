package lee.moonhyuk.blogsearch.ranking.event;

import lee.moonhyuk.blogsearch.ranking.domain.Keyword;
import lee.moonhyuk.blogsearch.ranking.domain.KeywordRepository;
import lee.moonhyuk.blogsearch.ranking.domain.TopRank;
import lee.moonhyuk.blogsearch.ranking.hit.HitService;
import lee.moonhyuk.blogsearch.ranking.hit.HitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HitServiceImplTest {

    private HitService hitService;

    @Mock
    private KeywordRepository keywordRepository;

    @Mock
    private TopRank topRank;

    @BeforeEach
    void setUp() {
        hitService = new HitServiceImpl(keywordRepository, topRank);
    }

    @Test
    void 새_키워드_등록시_hit_증가_및_top_rank_등록() {
        // Given
        String keyword = "example";
        when(keywordRepository.findByKeyword(keyword)).thenReturn(Optional.empty());
        when(keywordRepository.saveAndFlush(any(Keyword.class))).thenReturn(new Keyword(keyword));

        // When
        hitService.hit(keyword);

        // Then
        verify(keywordRepository).findByKeyword(keyword);
        verify(keywordRepository).saveAndFlush(any(Keyword.class));
        verify(topRank).insert(keyword, 1L);
    }

    @Test
    void 기존_키워드_갱신시_hit_증가_및_top_rank_업데이트() {
        // Given
        String keyword = "example";
        Keyword existingKeyword = new Keyword(keyword);
        when(keywordRepository.findByKeyword(keyword)).thenReturn(Optional.of(existingKeyword));
        when(keywordRepository.saveAndFlush(existingKeyword)).thenReturn(existingKeyword.increaseHit());

        // When
        hitService.hit(keyword);

        // Then
        verify(keywordRepository).findByKeyword(keyword);
        verify(keywordRepository).saveAndFlush(existingKeyword);
        verify(topRank).insert(keyword, existingKeyword.getHit());
    }

    @Test
    void 키워드_비었으면_오류발생() {
        // Given
        String keyword = "";

        // When
        assertThatCode(() -> hitService.hit(keyword))
                .isInstanceOf(IllegalArgumentException.class);

        // Then
        verify(keywordRepository, never()).findByKeyword(anyString());
        verify(keywordRepository, never()).saveAndFlush(any(Keyword.class));
        verify(topRank, never()).insert(anyString(), anyLong());
    }
}