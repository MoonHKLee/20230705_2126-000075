package lee.moonhyuk.blogsearch.search.dto;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.*;

public class BlogSearchRequestTest {

    @Test
    void constructor_정상동작() {
        // given
        String query = "example query";
        String sort = "recency";
        Integer page = 2;
        Integer size = 20;

        // when
        BlogSearchRequest request = new BlogSearchRequest(query, sort, page, size);

        // then
        assertThat(request.getQuery()).isEqualTo(query);
        assertThat(request.getSort()).isEqualTo("recency");
        assertThat(request.getPage()).isEqualTo(page);
        assertThat(request.getSize()).isEqualTo(size);
    }

    @Test
    void constructor_디폴트_테스트() {
        // given
        String query = "example query";

        // when
        BlogSearchRequest request = new BlogSearchRequest(query, null, null, null);

        // then
        assertThat(request.getQuery()).isEqualTo(query);
        assertThat(request.getSort()).isEqualTo("accuracy");
        assertThat(request.getPage()).isEqualTo(1);
        assertThat(request.getSize()).isEqualTo(10);
    }

    @Test
    void constructor_쿼리_빈값_테스트() {
        // given
        String query = " ";

        // when, then
        assertThatThrownBy(() -> new BlogSearchRequest(query, null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("query should not be blank");
    }

    @Test
    void getSort_정렬_테스트() {
        // given
        BlogSearchRequest request = new BlogSearchRequest("example query", "recency", null, null);

        // when
        String sortValue = request.getSort();

        // then
        assertThat(sortValue).isEqualTo("recency");
    }

    @Test
    void getSort_정렬_기본값_테스트() {
        // given
        BlogSearchRequest request = new BlogSearchRequest("example query", null, null, null);

        // when
        String sortValue = request.getSort();

        // then
        assertThat(sortValue).isEqualTo("accuracy");
    }

    @Test
    void validate_동작_테스트() {
        // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        BlogSearchRequest validRequest = new BlogSearchRequest("example query", null, 1, 10);

        // when
        boolean isValid = validator.validate(validRequest).isEmpty();

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    void validate_쿼리빈값_테스트() {
        assertThatCode(()->new BlogSearchRequest(" ", null, 1, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("query should not be blank");
    }

    @Test
    void validate_페이지_초과테스트() {
        // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        BlogSearchRequest invalidRequest = new BlogSearchRequest("example query", null, 51, 10);

        // when
        boolean isValid = validator.validate(invalidRequest).isEmpty();

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    void validate_페이지_미달_테스트() {
        // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        BlogSearchRequest invalidRequest = new BlogSearchRequest("example query", null, 1, -1);

        // when
        boolean isValid = validator.validate(invalidRequest).isEmpty();

        // then
        assertThat(isValid).isFalse();
    }
}