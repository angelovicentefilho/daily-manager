package br.com.jtech.services.daily.manager.application.core.validators;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import lombok.experimental.UtilityClass;

import java.util.UUID;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@UtilityClass
public class EmployeeValidator {
    public static void checkEmployee(Employee employee) {
        notNull(employee, "Employee cannot be null!");
        hasText(employee.getEmail(), "Employee email required!");
        hasText(employee.getName(), "Name is required!");
        hasText(employee.getPassword(), "Password required!");
        hasText(employee.getUsername(), "Username required!");
    }

    public static void checkEmail(String email) {
        hasText(email, "Employee email cannot be null!");
    }

    public static void checkId(String id) {
        try {
            var uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Id employee invalid!");
        }
    }

    public static void checkUsername(String username) {
        hasText(username, "Username cannot be null!");
    }
}
