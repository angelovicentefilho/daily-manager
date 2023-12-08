package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByIdOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static br.com.jtech.services.daily.manager.application.core.domains.employee.Employee.fromDocument;

@Component
@RequiredArgsConstructor
public class FindEmployeeByIdAdapter implements FindEmployeeByIdOutputGateway {

    private EmployeeRepository repository;

    @Override
    public Employee findById(UUID uuid) {
        return fromDocument(this.repository.findById(uuid)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found!")));
    }
}
