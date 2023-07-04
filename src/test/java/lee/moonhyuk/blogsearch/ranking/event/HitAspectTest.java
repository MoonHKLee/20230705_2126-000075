package lee.moonhyuk.blogsearch.ranking.event;

import lee.moonhyuk.blogsearch.ranking.hit.HitAspect;
import lee.moonhyuk.blogsearch.ranking.hit.HitService;
import lee.moonhyuk.blogsearch.util.ThreadLocalContext;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HitAspectTest {

    @Mock
    private HitService hitService;

    private HitAspect hitAspect;

    @BeforeEach
    void setUp() {
        hitAspect = new HitAspect(hitService);
    }

    @Test
    void AOP_제대로_적용되었는지_테스트() {
        // given
        ThreadLocalContext.threadLocal.set("test-value");
        JoinPoint joinPoint = null;

        // when
        hitAspect.afterReturningAdvice(joinPoint);

        // then
        verify(hitService).hit("test-value");
    }
}