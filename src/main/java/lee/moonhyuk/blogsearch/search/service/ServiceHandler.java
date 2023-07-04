package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiceHandler {

    private final List<BlogSearchService> serviceList;

    public ServiceHandler(List<BlogSearchService> serviceList) {
        this.serviceList = serviceList;
    }

    public BlogSearchResponse execute(BlogSearchRequest request, int index) {
        try {
            return serviceList.get(index).search(request);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("end of service list");
        } catch (Exception e) {
            index++;
            log.info("go next index to:{}",index);
            return execute(request, index);
        }
    }
}
