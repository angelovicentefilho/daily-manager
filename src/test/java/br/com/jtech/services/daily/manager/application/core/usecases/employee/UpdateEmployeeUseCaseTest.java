package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.UpdateEmployeeOuputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateEmployeeUseCaseTest {

    @Mock
    private UpdateEmployeeOuputGateway updateEmployeeOuputGateway;

    private UpdateEmployeeUseCase updateEmployeeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateEmployeeUseCase = new UpdateEmployeeUseCase(updateEmployeeOuputGateway);
    }

    @Test
    void testUpdateWhenEmployeeNotNullThenReturnUpdatedEmployee() {
        Employee employee = Employee.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .build();
        when(updateEmployeeOuputGateway.update(employee)).thenReturn(Optional.of(employee));
        Optional<Employee> updatedEmployee = updateEmployeeUseCase.update(employee);
        assertTrue(updatedEmployee.isPresent());
        assertEquals(employee, updatedEmployee.get());
        verify(updateEmployeeOuputGateway).update(employee);
    }
}