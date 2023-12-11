package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.output.employee.DeleteEmployeeOuputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByIdOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeUseCaseTest {

    @Mock
    private DeleteEmployeeOuputGateway deleteEmployeeOuputGateway;
    @Mock
    private FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;
    @Mock
    private FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;
    @Mock
    private FindEmployeeByIdOutputGateway findEmployeeByIdOutputGateway;

    @InjectMocks
    private DeleteEmployeeUseCase deleteEmployeeUseCase;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(UUID.randomUUID(), "John Doe", "johndoe", "password", "john.doe@example.com");
    }

    @Test
    void testDeleteByEmailWhenEmployeeIsFoundThenDeleteSuccessfully() {
        when(findEmployeeByEmailOutputGateway.findByEmail(any(String.class))).thenReturn(Optional.of(employee));
        deleteEmployeeUseCase.deleteByEmail(employee.getEmail());
        verify(deleteEmployeeOuputGateway).deleteById(employee.getId());
    }

    @Test
    void testDeleteByEmailWhenEmployeeIsNotFoundThenDoNothing() {
        String nonExistentEmail = "nonexistent@example.com";
        when(findEmployeeByEmailOutputGateway.findByEmail(nonExistentEmail))
                .thenThrow(new EmployeeNotFoundException("Employee not found!"));
        assertThrows(EmployeeNotFoundException.class, () -> deleteEmployeeUseCase.deleteByEmail(nonExistentEmail));
        verify(deleteEmployeeOuputGateway, never()).deleteById(any(UUID.class));
    }

    @Test
    void testDeleteByIdWhenEmployeeIsFoundThenDeleteSuccessfully() {
        when(findEmployeeByIdOutputGateway.findById(any(UUID.class))).thenReturn(Optional.of(employee));
        deleteEmployeeUseCase.deleteById(employee.getId().toString());
        verify(deleteEmployeeOuputGateway).deleteById(employee.getId());
    }

    @Test
    void testDeleteByIdWhenEmployeeIsNotFoundThenDoNothing() {
        when(findEmployeeByIdOutputGateway.findById(any(UUID.class)))
                .thenThrow(new EmployeeNotFoundException("Employee not found!"));
        assertThrows(EmployeeNotFoundException.class, () -> deleteEmployeeUseCase.deleteById(UUID.randomUUID().toString()));
        verify(deleteEmployeeOuputGateway, never()).deleteById(any(UUID.class));
    }

    @Test
    void testDeleteByUsernameWhenEmployeeIsFoundThenDeleteSuccessfully() {
        when(findEmployeeByUsernameOutputGateway.findByUsername(any(String.class))).thenReturn(Optional.of(employee));
        deleteEmployeeUseCase.deleteByUsername(employee.getUsername());
        verify(deleteEmployeeOuputGateway).deleteById(employee.getId());
    }

    @Test
    void testDeleteByUsernameWhenEmployeeIsNotFoundThenDoNothing() {
        when(findEmployeeByUsernameOutputGateway.findByUsername(any(String.class)))
                .thenThrow(new EmployeeNotFoundException("Employee not found!"));
        assertThrows(EmployeeNotFoundException.class, () -> deleteEmployeeUseCase.deleteByUsername("nonexistent"));
        verify(deleteEmployeeOuputGateway, never()).deleteById(any(UUID.class));
    }

    @Test
    void testDeleteAll() {
        deleteEmployeeUseCase.deleteAll();
        verify(deleteEmployeeOuputGateway).deleteAll();
    }
}
