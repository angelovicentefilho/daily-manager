package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindEmployeeByUsernameAdapter implements FindEmployeeByUsernameOutputGateway {

    private final EmployeeRepository repository;

    @Override
    public Employee findByUsername(String username) {
        return Employee.fromDocument(this.repository.findByUsername(username)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found!")));
    }
}
