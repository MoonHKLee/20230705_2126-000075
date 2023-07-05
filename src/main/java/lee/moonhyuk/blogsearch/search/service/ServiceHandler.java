package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.exception.ServiceListEndException;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceHandler {

    private final List<BlogSearchService> serviceList;

    public BlogSearchResponse execute(BlogSearchRequest request, int index) {
        try {
            return serviceList.get(index).search(request);
        } catch (IndexOutOfBoundsException e) {
            throw new ServiceListEndException("end of service list");
        } catch (Exception e) {
            index++;
            log.info("go next index to:{}",index);
            return execute(request, index);
        }
    }
}
