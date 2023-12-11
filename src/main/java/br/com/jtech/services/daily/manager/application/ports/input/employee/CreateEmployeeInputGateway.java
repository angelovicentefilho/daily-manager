package br.com.jtech.services.daily.manager.application.ports.input.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

import java.util.Optional;

public interface CreateEmployeeInputGateway {
    Optional<Employee> create(Employee employee);
}
