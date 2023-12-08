package br.com.jtech.services.daily.manager.adapters.input.protocols.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EmployeeResponseTest {

    private final UUID ID = UUID.randomUUID();
    private final UUID ID2 = UUID.randomUUID();

    @Test
    public void testFromDomain() {
        Employee employee = new Employee();
        employee.setId(ID);
        employee.setName("John Doe");
        employee.setUsername("johndoe");
        employee.setPassword("password123");
        employee.setEmail("john.doe@example.com");

        EmployeeResponse employeeResponse = EmployeeResponse.fromDomain(employee);

        assertEquals(employee.getId().toString(), employeeResponse.getId());
        assertEquals(employee.getName(), employeeResponse.getName());
        assertEquals(employee.getUsername(), employeeResponse.getUsername());
        assertEquals(employee.getPassword(), employeeResponse.getPassword());
        assertEquals(employee.getEmail(), employeeResponse.getEmail());
        assertNull(employeeResponse.getEmployees());
    }

    @Test
    public void testFromDomains() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId(ID);
        employee1.setName("John Doe");
        employee1.setUsername("johndoe");
        employee1.setPassword("password123");
        employee1.setEmail("john.doe@example.com");
        employees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setId(ID2);
        employee2.setName("Jane Smith");
        employee2.setUsername("janesmith");
        employee2.setPassword("password456");
        employee2.setEmail("jane.smith@example.com");
        employees.add(employee2);

        EmployeeResponse employeeResponse = EmployeeResponse.fromDomains(employees);

        assertNull(employeeResponse.getId());
        assertNull(employeeResponse.getName());
        assertNull(employeeResponse.getUsername());
        assertNull(employeeResponse.getPassword());
        assertNull(employeeResponse.getEmail());
        assertEquals(2, employeeResponse.getEmployees().size());
        assertEquals(employee1.getId().toString(), employeeResponse.getEmployees().get(0).getId());
        assertEquals(employee1.getName(), employeeResponse.getEmployees().get(0).getName());
        assertEquals(employee1.getUsername(), employeeResponse.getEmployees().get(0).getUsername());
        assertEquals(employee1.getPassword(), employeeResponse.getEmployees().get(0).getPassword());
        assertEquals(employee1.getEmail(), employeeResponse.getEmployees().get(0).getEmail());
        assertEquals(employee2.getId().toString(), employeeResponse.getEmployees().get(1).getId());
        assertEquals(employee2.getName(), employeeResponse.getEmployees().get(1).getName());
        assertEquals(employee2.getUsername(), employeeResponse.getEmployees().get(1).getUsername());
        assertEquals(employee2.getPassword(), employeeResponse.getEmployees().get(1).getPassword());
        assertEquals(employee2.getEmail(), employeeResponse.getEmployees().get(1).getEmail());
    }
}