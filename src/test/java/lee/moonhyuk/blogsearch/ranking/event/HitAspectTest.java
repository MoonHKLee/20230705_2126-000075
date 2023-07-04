package lee.moonhyuk.blogsearch.ranking.event;

import lee.moonhyuk.blogsearch.ranking.hit.HitAspect;
import lee.moonhyuk.blogsearch.ranking.hit.HitService;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HitAspectTest {

    @Mock
    private HitService hitService;

    @InjectMocks
    private HitAspect hitAspect;

    @Captor
    private ArgumentCaptor<String> queryCaptor;

    @Test
    void Hit_애노테이션_적용시_제대로_파라미터_가져오는지() {
        // Given
        HitAspect aspect = new HitAspect(hitService);
        BlogSearchRequest request = new BlogSearchRequest("query","accuracy",1,10);

        JoinPoint joinPoint = mock(JoinPoint.class);
        given(joinPoint.getArgs()).willReturn(new Object[]{request});

        // When
        aspect.afterReturningAdvice(joinPoint);

        // Then
        verify(hitService).hit(queryCaptor.capture());
        assertThat(queryCaptor.getValue()).isEqualTo(request.getQuery());
    }
}