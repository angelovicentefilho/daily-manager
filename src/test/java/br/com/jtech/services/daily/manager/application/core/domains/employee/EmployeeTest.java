package br.com.jtech.services.daily.manager.application.core.domains.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private EmployeeDocument employeeDocument;
    private EmployeeRequest employeeRequest;

    @BeforeEach
    void setUp() {
        employeeDocument = EmployeeDocument.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .username("johndoe")
                .password("password123")
                .email("john.doe@example.com")
                .build();

        employeeRequest = EmployeeRequest.builder()
                .id(employeeDocument.getId().toString())
                .name(employeeDocument.getName())
                .username(employeeDocument.getUsername())
                .password(employeeDocument.getPassword())
                .email(employeeDocument.getEmail())
                .build();
    }

    @Test
    @DisplayName("test 'fromDocuments' method with a non-empty list of documents")
    void testFromDocumentsWhenDocumentsNotEmptyThenReturnListOfEmployees() {
        List<EmployeeDocument> documents = List.of(employeeDocument);

        List<Employee> employees = Employee.fromDocuments(documents);

        assertNotNull(employees);
        assertEquals(documents.size(), employees.size());
    }

    @Test
    @DisplayName("test 'fromDocuments' method with an empty list of documents")
    void testFromDocumentsWhenDocumentsEmptyThenReturnEmptyList() {
        List<EmployeeDocument> documents = Collections.emptyList();

        List<Employee> employees = Employee.fromDocuments(documents);

        assertNotNull(employees);
        assertTrue(employees.isEmpty());
    }

    @Test
    @DisplayName("test 'toDocument' method with a non-null 'Employee' object")
    void testToDocumentWhenEmployeeNotNullThenReturnEmployeeDocument() {
        Employee employee = Employee.builder()
                .id(employeeDocument.getId())
                .name(employeeDocument.getName())
                .username(employeeDocument.getUsername())
                .password(employeeDocument.getPassword())
                .email(employeeDocument.getEmail())
                .build();

        EmployeeDocument document = employee.toDocument();

        assertNotNull(document);
        assertEquals(employee.getId(), document.getId());
        assertEquals(employee.getName(), document.getName());
        assertEquals(employee.getUsername(), document.getUsername());
        assertEquals(employee.getPassword(), document.getPassword());
        assertEquals(employee.getEmail(), document.getEmail());
    }

    @Test
    @DisplayName("test 'fromDocument' method with a non-null 'EmployeeDocument' object")
    void testFromDocumentWhenDocumentNotNullThenReturnEmployee() {
        Employee employee = Employee.fromDocument(employeeDocument);

        assertNotNull(employee);
        assertEquals(employeeDocument.getId(), employee.getId());
        assertEquals(employeeDocument.getName(), employee.getName());
        assertEquals(employeeDocument.getUsername(), employee.getUsername());
        assertEquals(employeeDocument.getPassword(), employee.getPassword());
        assertEquals(employeeDocument.getEmail(), employee.getEmail());
    }

    @Test
    @DisplayName("test 'fromRequest' method with a non-null 'EmployeeRequest' object")
    void testFromRequestWhenRequestNotNullThenReturnEmployeeWithId() {
        Employee employee = Employee.fromRequest(employeeRequest);

        assertNotNull(employee);
        assertEquals(employeeRequest.getName(), employee.getName());
        assertEquals(employeeRequest.getUsername(), employee.getUsername());
        assertEquals(employeeRequest.getPassword(), employee.getPassword());
        assertEquals(employeeRequest.getEmail(), employee.getEmail());
        assertNotNull(employee.getId());
    }
}