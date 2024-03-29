package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.UpdateEmployeeOuputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.jtech.services.daily.manager.application.core.domains.employee.Employee.fromDocument;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeAdapter implements UpdateEmployeeOuputGateway {

    private final EmployeeRepository repository;

    @Override
    public Optional<Employee> update(Employee employee) {
        return Optional.of(fromDocument(repository.save(employee.toDocument())));
    }
}
