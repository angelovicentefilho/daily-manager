package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.jtech.services.daily.manager.application.core.domains.employee.Employee.fromDocument;

@Component
@RequiredArgsConstructor
public class FindEmployeeByEmailAdapter implements FindEmployeeByEmailOutputGateway {

    private final EmployeeRepository repository;

    @Override
    public Optional<Employee> findByEmail(String email) {
        return Optional.of(fromDocument(repository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found!"))));
    }
}
