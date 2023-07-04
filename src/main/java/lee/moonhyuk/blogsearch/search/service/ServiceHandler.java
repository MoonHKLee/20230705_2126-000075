package lee.moonhyuk.blogsearch.search.service;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceHandler {

    private final List<BlogSearchService> serviceList;

    public ServiceHandler(List<BlogSearchService> serviceList) {
        this.serviceList = serviceList;
    }

    public BlogSearchResponse execute(BlogSearchRequest request, int index) {
        try {
            return serviceList.get(index).search(request);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("목록끝");
        } catch (Exception e) {
            index++;
            System.out.println("go next to" + index);
            return execute(request, index);
        }
    }
}
