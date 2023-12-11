package br.com.jtech.services.daily.manager.application.ports.input.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

import java.util.Optional;

public interface UpdateEmployeeInputGateway {
    Optional<Employee> update(Employee request);
}
