package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateEmployeeAdapterTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private CreateEmployeeAdapter createEmployeeAdapter;

    @Test
    public void testCreateEmployee() {
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

        EmployeeDocument mockEmployeeDocument = EmployeeDocument.builder()
                .id(id)
                .name(name)
                .username(username)
                .password(password)
                .email(email)
                .build();

        when(repository.save(any(EmployeeDocument.class))).thenReturn(mockEmployeeDocument);

        Optional<Employee> createdEmployee = createEmployeeAdapter.create(mockEmployee);

        verify(repository, times(1)).save(any(EmployeeDocument.class));

        assertTrue(createdEmployee.isPresent());
        assertEquals(id, createdEmployee.get().getId());
        assertEquals(name, createdEmployee.get().getName());
        assertEquals(username, createdEmployee.get().getUsername());
        assertEquals(password, createdEmployee.get().getPassword());
        assertEquals(email, createdEmployee.get().getEmail());
    }
}
