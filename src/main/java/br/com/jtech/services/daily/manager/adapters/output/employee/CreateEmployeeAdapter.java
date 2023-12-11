package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.CreateEmployeeOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CreateEmployeeAdapter implements CreateEmployeeOutputGateway {

    private final EmployeeRepository repository;

    @Override
    public Optional<Employee> create(Employee employee) {
        return Optional.of(Employee.fromDocument(this.repository.save(employee.toDocument())));
    }
}
