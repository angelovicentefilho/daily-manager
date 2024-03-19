package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.Role;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.CreateEmployeeInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.CreateEmployeeOutputGateway;

import java.util.Optional;
import java.util.Set;


public class CreateEmployeeUseCase implements CreateEmployeeInputGateway {

    private final CreateEmployeeOutputGateway createEmployeeOutputGateway;

    public CreateEmployeeUseCase(CreateEmployeeOutputGateway createEmployeeOutputGateway) {
        this.createEmployeeOutputGateway = createEmployeeOutputGateway;
    }

    @Override
    public Optional<Employee> create(Employee employee) {
        setDevRole(employee);
        return createEmployeeOutputGateway.create(employee);
    }

    private void setDevRole(Employee employee) {
        if (employee.isNotRoles()) {
            employee.setRoles(Set.of(Role.DEV));
        }
    }
}
