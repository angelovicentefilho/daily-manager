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
public class FindEmployeeByIdAdapterTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private FindEmployeeByIdAdapter findEmployeeByIdAdapter;

    @Test
    public void testFindByIdExistingEmployee() {
        UUID id = UUID.randomUUID();
        String name = "John Doe";
        String username = "johndoe";
        String password = "pass123";
        String email = "john@example.com";

        Employee mockEmployee = Employee.builder()
                .id(id)
                .name(name)
                .username(username)
                .password(password)
                .email(email)
                .build();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(mockEmployee.toDocument()));

        Employee foundEmployee = findEmployeeByIdAdapter.findById(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);

        assertEquals(id, foundEmployee.getId());
        assertEquals(name, foundEmployee.getName());
        assertEquals(username, foundEmployee.getUsername());
        assertEquals(password, foundEmployee.getPassword());
        assertEquals(email, foundEmployee.getEmail());
    }

    @Test
    public void testFindByIdNonExistingEmployee() {
        UUID nonExistingId = UUID.randomUUID();

        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class,
                () -> findEmployeeByIdAdapter.findById(nonExistingId));

        Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);

        assertEquals("Employee not found!", exception.getMessage());
    }
}
