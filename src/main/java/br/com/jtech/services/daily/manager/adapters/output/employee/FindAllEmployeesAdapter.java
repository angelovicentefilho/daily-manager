package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindAllEmployeesOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.jtech.services.daily.manager.application.core.domains.employee.Employee.fromDocuments;

@Component
@RequiredArgsConstructor
public class FindAllEmployeesAdapter implements FindAllEmployeesOutputGateway {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return fromDocuments(this.repository.findAll());
    }

}
