package lee.moonhyuk.blogsearch.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringListValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedStrings {

    String[] value();

    String message() default "Invalid string provided.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}