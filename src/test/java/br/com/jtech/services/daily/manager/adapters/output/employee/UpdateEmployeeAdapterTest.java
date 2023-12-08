package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
public class UpdateEmployeeAdapterTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private UpdateEmployeeAdapter updateEmployeeAdapter;

    @Test
    public void testUpdateEmployee() {
        Employee mockEmployee = Employee.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .username("johndoe")
                .password("pass123")
                .email("john@example.com")
                .build();

        Mockito.when(repository.save(any())).thenReturn(mockEmployee.toDocument());

        Employee updatedEmployee = updateEmployeeAdapter.update(mockEmployee);

        Mockito.verify(repository, times(1)).save(any());

        assertEquals(mockEmployee.getId(), updatedEmployee.getId());
        assertEquals(mockEmployee.getName(), updatedEmployee.getName());
        assertEquals(mockEmployee.getUsername(), updatedEmployee.getUsername());
        assertEquals(mockEmployee.getPassword(), updatedEmployee.getPassword());
        assertEquals(mockEmployee.getEmail(), updatedEmployee.getEmail());
    }
}
