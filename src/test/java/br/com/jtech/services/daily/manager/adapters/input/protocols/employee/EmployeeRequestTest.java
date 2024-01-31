package br.com.jtech.services.daily.manager.adapters.input.protocols.employee;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEmployeeRequestValidation() {
        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                .id("1")
                .name("")
                .username("")
                .password("123")
                .email("invalid-email")
                .build();

        Set<ConstraintViolation<EmployeeRequest>> violations = validator.validate(employeeRequest);

        assertEquals(4, violations.size());
        for (ConstraintViolation<EmployeeRequest> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            switch (propertyPath) {
                case "name" -> assertEquals("Name cannot be empty!", message);
                case "username" -> assertEquals("Username cannot be empty!", message);
                case "password" -> assertEquals("Password min 5 and max 20 characters!", message);
                case "email" -> assertEquals("Email is not valid!", message);
            }
        }
    }
}