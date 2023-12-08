package br.com.jtech.services.daily.manager.adapters.output.repositories;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindByEmailWhenEmailExistsThenReturnEmployeeDocument() {
        String email = "test@example.com";
        EmployeeDocument expectedEmployee = new EmployeeDocument();
        expectedEmployee.setEmail(email);
        when(employeeRepository.findByEmail(email)).thenReturn(Optional.of(expectedEmployee));
        Optional<EmployeeDocument> result = employeeRepository.findByEmail(email);
        assertTrue(result.isPresent(), "Employee should be found");
        assertEquals(expectedEmployee, result.get(), "The returned employee should match the expected one");
    }

    @Test
    public void testFindByEmailWhenEmailDoesNotExistThenReturnEmptyOptional() {
        String email = "nonexistent@example.com";
        when(employeeRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<EmployeeDocument> result = employeeRepository.findByEmail(email);
        assertFalse(result.isPresent(), "No employee should be found");
    }

    @Test
    public void testFindByUsernameWhenUsernameExistsThenReturnEmployeeDocument() {
        String username = "testuser";
        EmployeeDocument expectedEmployee = new EmployeeDocument();
        expectedEmployee.setUsername(username);
        when(employeeRepository.findByUsername(username)).thenReturn(Optional.of(expectedEmployee));
        Optional<EmployeeDocument> result = employeeRepository.findByUsername(username);
        assertTrue(result.isPresent(), "Employee should be found");
        assertEquals(expectedEmployee, result.get(), "The returned employee should match the expected one");
    }

    @Test
    public void testFindByUsernameWhenUsernameDoesNotExistThenReturnEmptyOptional() {
        String username = "nonexistentuser";
        when(employeeRepository.findByUsername(username)).thenReturn(Optional.empty());
        Optional<EmployeeDocument> result = employeeRepository.findByUsername(username);
        assertFalse(result.isPresent(), "No employee should be found");
    }
}
