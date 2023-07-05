package lee.moonhyuk.blogsearch.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringListValidator implements ConstraintValidator<AllowedStrings, String> {

    private String[] allowedStrings;

    @Override
    public void initialize(AllowedStrings constraintAnnotation) {
        allowedStrings = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String inputString, ConstraintValidatorContext context) {
        if (inputString == null) {
            return true;
        }
        
        for (String allowedString : allowedStrings) {
            if (allowedString.equals(inputString)) {
                return true;
            }
        }
        
        return false;
    }
}