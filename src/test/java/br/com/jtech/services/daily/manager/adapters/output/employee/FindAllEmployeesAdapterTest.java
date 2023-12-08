package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FindAllEmployeesAdapterTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private FindAllEmployeesAdapter findAllEmployeesAdapter;

    @Test
    public void testFindAllEmployees() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        String name1 = "John Doe";
        String name2 = "Jane Smith";
        String username1 = "johndoe";
        String username2 = "janesmith";
        String password1 = "pass123";
        String password2 = "pass456";
        String email1 = "john@example.com";
        String email2 = "jane@example.com";

        EmployeeDocument employeeDocument1 = EmployeeDocument.builder()
                .id(id1)
                .name(name1)
                .username(username1)
                .password(password1)
                .email(email1)
                .build();

        EmployeeDocument employeeDocument2 = EmployeeDocument.builder()
                .id(id2)
                .name(name2)
                .username(username2)
                .password(password2)
                .email(email2)
                .build();

        List<EmployeeDocument> mockEmployeeDocuments = Arrays.asList(employeeDocument1, employeeDocument2);

        Mockito.when(repository.findAll()).thenReturn(mockEmployeeDocuments);

        List<Employee> foundEmployees = findAllEmployeesAdapter.findAll();

        Mockito.verify(repository, Mockito.times(1)).findAll();

        assertEquals(2, foundEmployees.size());

        assertEquals(id1, foundEmployees.get(0).getId());
        assertEquals(name1, foundEmployees.get(0).getName());
        assertEquals(username1, foundEmployees.get(0).getUsername());
        assertEquals(password1, foundEmployees.get(0).getPassword());
        assertEquals(email1, foundEmployees.get(0).getEmail());

        assertEquals(id2, foundEmployees.get(1).getId());
        assertEquals(name2, foundEmployees.get(1).getName());
        assertEquals(username2, foundEmployees.get(1).getUsername());
        assertEquals(password2, foundEmployees.get(1).getPassword());
        assertEquals(email2, foundEmployees.get(1).getEmail());
    }
}
