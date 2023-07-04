package lee.moonhyuk.blogsearch.api;

import lee.moonhyuk.blogsearch.search.dto.BlogSearchRequest;
import lee.moonhyuk.blogsearch.search.dto.BlogSearchResponse;
import lee.moonhyuk.blogsearch.search.service.ServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BlogSearchController {
    private final ServiceHandler serviceHandler;

    @GetMapping("/blogs")
    public ResponseEntity<BlogSearchResponse> search(@Valid BlogSearchRequest request) {
        return ResponseEntity.ok(serviceHandler.execute(request, 0));
    }
}
