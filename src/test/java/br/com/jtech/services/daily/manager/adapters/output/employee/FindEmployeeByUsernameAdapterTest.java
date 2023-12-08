package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
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
public class FindEmployeeByUsernameAdapterTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private FindEmployeeByUsernameAdapter findEmployeeByUsernameAdapter;

    @Test
    public void testFindByUsernameExistingEmployee() {
        String username = "johndoe";

        Employee mockEmployee = Employee.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .username(username)
                .password("pass123")
                .email("john@example.com")
                .build();

        Mockito.when(repository.findByUsername(username)).thenReturn(Optional.of(mockEmployee.toDocument()));

        Employee foundEmployee = findEmployeeByUsernameAdapter.findByUsername(username);

        Mockito.verify(repository, Mockito.times(1)).findByUsername(username);

        assertEquals(username, foundEmployee.getUsername());
    }

    @Test
    public void testFindByUsernameNonExistingEmployee() {
        String nonExistingUsername = "nonexistinguser";

        Mockito.when(repository.findByUsername(nonExistingUsername)).thenReturn(Optional.empty());

        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class,
                () -> findEmployeeByUsernameAdapter.findByUsername(nonExistingUsername));

        Mockito.verify(repository, Mockito.times(1)).findByUsername(nonExistingUsername);

        assertEquals("Employee not found!", exception.getMessage());
    }
}
