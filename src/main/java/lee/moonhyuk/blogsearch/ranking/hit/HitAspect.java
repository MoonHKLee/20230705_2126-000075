package lee.moonhyuk.blogsearch.ranking.hit;

import lee.moonhyuk.blogsearch.util.ThreadLocalContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Aspect
public class HitAspect {
    private final HitService hitService;

    @AfterReturning(pointcut = "@annotation(lee.moonhyuk.blogsearch.ranking.hit.Hit)")
    public void afterReturningAdvice(JoinPoint joinPoint) {
        hitService.hit(ThreadLocalContext.threadLocal.get());
        ThreadLocalContext.threadLocal.remove();
    }
}