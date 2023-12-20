package br.com.jtech.services.daily.manager.adapters.input.protocols.squad;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquadRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSquadRequestValidation() {
        SquadRequest squadRequest = SquadRequest.builder()
                .id("1")
                .name("")
                .description("")
                .members(null)
                .maxCapacity(null)
                .isPublic(null)
                .build();

        Set<ConstraintViolation<SquadRequest>> violations = validator.validate(squadRequest);

        assertEquals(1, violations.size());
        for (ConstraintViolation<SquadRequest> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            switch (propertyPath) {
                case "name" -> assertEquals("Name cannot be empty!", message);
            }
        }
    }
}