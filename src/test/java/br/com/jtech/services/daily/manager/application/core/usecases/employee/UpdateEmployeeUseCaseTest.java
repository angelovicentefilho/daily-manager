package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.UpdateEmployeeOuputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(updateEmployeeOuputGateway.update(employee)).thenReturn(employee);
        Employee updatedEmployee = updateEmployeeUseCase.update(employee);
        assertEquals(employee, updatedEmployee);
        verify(updateEmployeeOuputGateway).update(employee);
    }
}