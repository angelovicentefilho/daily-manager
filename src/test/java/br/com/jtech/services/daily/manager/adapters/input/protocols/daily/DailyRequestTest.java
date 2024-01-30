package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DailyRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testDailyRequestValidation() {
        DailyRequest request = DailyRequest.builder()
                .id("1")
                .squadId("")
                .authorEmail("")
                .summary("123")
                .build();

        Set<ConstraintViolation<DailyRequest>> violations = validator.validate(request);

        assertEquals(2, violations.size());
        for (ConstraintViolation<DailyRequest> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            switch (propertyPath) {
                case "squadId" -> assertEquals("Squad cannot be empty!", message);
                case "authorEmail" -> assertEquals("Author email cannot be empty!", message);
            }
        }
    }
}