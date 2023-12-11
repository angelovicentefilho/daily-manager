package br.com.jtech.services.daily.manager.application.ports.output.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

import java.util.Optional;

public interface FindEmployeeByEmailOutputGateway {
    Optional<Employee> findByEmail(String email);
}
