package br.com.jtech.services.daily.manager.application.ports.output.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

import java.util.Optional;
import java.util.UUID;

public interface FindEmployeeByIdOutputGateway {
    Optional<Employee> findById(UUID uuid);
}
