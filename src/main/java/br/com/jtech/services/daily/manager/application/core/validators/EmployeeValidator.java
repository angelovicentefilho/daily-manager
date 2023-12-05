package br.com.jtech.services.daily.manager.application.core.validators;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import lombok.experimental.UtilityClass;

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
}
