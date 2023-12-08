package br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee;

import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDocumentTest {

    private static final UUID ID = GenId.newUuid();

    @Test
    void testEmployeeDocumentFields() {
        String name = "John Doe";
        String username = "johndoe";
        String password = "pass123";
        String email = "john@example.com";

        EmployeeDocument employeeDocument = EmployeeDocument.builder()
                .name(name)
                .username(username)
                .password(password)
                .email(email)
                .build();

        assertEquals(name, employeeDocument.getName());
        assertEquals(username, employeeDocument.getUsername());
        assertEquals(password, employeeDocument.getPassword());
        assertEquals(email, employeeDocument.getEmail());
    }

    @Test
    void testEmployeeDocumentEqualsAndHashCode() {
        EmployeeDocument employeeDocument1 = EmployeeDocument.builder()
                .id(ID)
                .name("John")
                .password("pass123")
                .username("johndoe")
                .email("john@example.com")
                .build();
        EmployeeDocument employeeDocument2 = EmployeeDocument.builder()
                .id(ID)
                .name("John")
                .password("pass123")
                .username("johndoe")
                .email("john@example.com")
                .build();
        assertEquals(employeeDocument1, employeeDocument2);
        assertEquals(employeeDocument1.hashCode(), employeeDocument2.hashCode());

    }

}
