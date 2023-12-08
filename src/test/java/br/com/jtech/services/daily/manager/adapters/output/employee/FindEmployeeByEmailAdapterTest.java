package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FindEmployeeByEmailAdapterTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private FindEmployeeByEmailAdapter findEmployeeByEmailAdapter;

    @Test
    public void testFindByEmailExistingEmployee() {
        UUID id = UUID.randomUUID();
        String name = "John Doe";
        String username = "johndoe";
        String password = "pass123";
        String email = "john@example.com";

        EmployeeDocument employeeDocument = EmployeeDocument.builder()
                .id(id)
                .name(name)
                .username(username)
                .password(password)
                .email(email)
                .build();

        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(employeeDocument));

        Employee foundEmployee = findEmployeeByEmailAdapter.findByEmail(email);

        Mockito.verify(repository, Mockito.times(1)).findByEmail(email);

        assertEquals(id, foundEmployee.getId());
        assertEquals(name, foundEmployee.getName());
        assertEquals(username, foundEmployee.getUsername());
        assertEquals(password, foundEmployee.getPassword());
        assertEquals(email, foundEmployee.getEmail());
    }

    @Test
    public void testFindByEmailNonExistingEmployee() {
        String nonExistingEmail = "nonexisting@example.com";
        Mockito.when(repository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class,
                () -> findEmployeeByEmailAdapter.findByEmail(nonExistingEmail));
        Mockito.verify(repository, Mockito.times(1)).findByEmail(nonExistingEmail);
        assertEquals("Employee not found!", exception.getMessage());
    }
}
