package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.CreateEmployeeOutputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEmployeeUseCaseTest {

    @Mock
    private CreateEmployeeOutputGateway createEmployeeOutputGateway;

    private CreateEmployeeUseCase createEmployeeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createEmployeeUseCase = new CreateEmployeeUseCase(createEmployeeOutputGateway);
    }

    @Test
    void testCreateWhenEmployeeNotNullThenReturnCreatedEmployee() {
        Employee employee = Employee.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .build();

        when(createEmployeeOutputGateway.create(employee)).thenReturn(Optional.of(employee));
        Optional<Employee> createdEmployee = createEmployeeUseCase.create(employee);
        assertTrue(createdEmployee.isPresent());
        assertEquals(employee, createdEmployee.get());
        verify(createEmployeeOutputGateway).create(employee);
    }

    @Test
    void testCreateEmployee_Success() {
        CreateEmployeeOutputGateway outputGateway = mock(CreateEmployeeOutputGateway.class);
        CreateEmployeeUseCase createEmployeeUseCase = new CreateEmployeeUseCase(outputGateway);
        Employee employee = new Employee();
        when(outputGateway.create(employee)).thenReturn(Optional.of(employee));
        Optional<Employee> createdEmployee = createEmployeeUseCase.create(employee);
        verify(outputGateway, times(1)).create(employee);
        assertTrue(createdEmployee.isPresent());
        assertEquals(employee, createdEmployee.get());
    }


    @Test
    void testCreateEmployee_ExceptionHandling() {
        CreateEmployeeOutputGateway outputGateway = mock(CreateEmployeeOutputGateway.class);
        CreateEmployeeUseCase createEmployeeUseCase = new CreateEmployeeUseCase(outputGateway);
        Employee employee = new Employee();
        when(outputGateway.create(employee)).thenThrow(new RuntimeException("Failed to create employee"));
        assertThrows(RuntimeException.class, () -> createEmployeeUseCase.create(employee));
        verify(outputGateway, times(1)).create(employee);
    }


}