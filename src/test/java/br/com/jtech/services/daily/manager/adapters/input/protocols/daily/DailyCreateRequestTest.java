package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DailyCreateRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidDailyCreateRequest() {
        // Arrange
        DailyCreateRequest request = new DailyCreateRequest();
        request.setSquadId("Test Squad");
        request.setAuthorEmail("test@example.com");
        request.setSummary("Test summary");

        // Act
        Set<ConstraintViolation<DailyCreateRequest>> violations = validator.validate(request);

        // Assert
        assertThat(violations).isEmpty();
    }

    @Test
    public void testInvalidDailyCreateRequestWithEmptySquadId() {
        // Arrange
        DailyCreateRequest request = new DailyCreateRequest();
        request.setSquadId("");
        request.setAuthorEmail("test@example.com");
        request.setSummary("Test summary");

        // Act
        Set<ConstraintViolation<DailyCreateRequest>> violations = validator.validate(request);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Squad cannot be empty!");
    }

    @Test
    public void testInvalidDailyCreateRequestWithInvalidAuthorEmail() {
        // Arrange
        DailyCreateRequest request = new DailyCreateRequest();
        request.setSquadId("Test Squad");
        request.setAuthorEmail("invalid_email");
        request.setSummary("Test summary");

        // Act
        Set<ConstraintViolation<DailyCreateRequest>> violations = validator.validate(request);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Author email is not valid!");
    }
}