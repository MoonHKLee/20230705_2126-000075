package lee.moonhyuk.blogsearch.acceptance;

import lee.moonhyuk.blogsearch.ranking.Keyword;
import lee.moonhyuk.blogsearch.ranking.KeywordRepository;
import lee.moonhyuk.blogsearch.ranking.event.HitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ConcurrencyTest {

    @Autowired
    private HitService hitService;

    @Autowired
    private KeywordRepository keywordRepository;

    @Test
    @Transactional
    void hit_멀티쓰레드_수량_정상_증가() throws InterruptedException {
        //given
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                        try {
                            //when
                            hitService.hit("test");
                        }
                        finally {
                            latch.countDown();
                        }
                    }
            );
        }

        latch.await();

        //then
        Keyword keyword = keywordRepository.findByKeyword("test").get();
        assertThat(keyword.getHit()).isEqualTo(1000);
    }
}
