package br.com.jtech.services.daily.manager.application.core.validators;

import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeValidatorTest {

    @Test
    void testValidateIdWithValidId() {
        String id = "123e4567-e89b-12d3-a456-556642440000";
        EmployeeValidator.validateId(id);
    }

    @Test
    void testValidateIdWithNullId() {
        String id = null;
        assertThrows(IllegalArgumentException.class, () -> EmployeeValidator.validateId(id));
    }

    @Test
    void testValidateIdWithInvalidId() {
        String id = "invalid-id";
        assertThrows(IllegalArgumentException.class, () -> EmployeeValidator.validateId(id));
    }

    @Test
    void testValidateIdNotNullWithNonNullId() {
        UUID id = GenId.newUuid("123e4567-e89b-12d3-a456-556642440000");
        EmployeeValidator.validateIdNotNull(id);
    }

    @Test
    void testValidateIdNotNullWithNullId() {
        UUID id = null;
        assertThrows(IllegalArgumentException.class, () -> EmployeeValidator.validateIdNotNull(id));
    }
}