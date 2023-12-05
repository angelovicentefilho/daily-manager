package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.CreateEmployeeInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.CreateEmployeeOutputGateway;

import static br.com.jtech.services.daily.manager.application.core.validators.EmployeeValidator.checkEmployee;

public class CreateEmployeeUseCase implements CreateEmployeeInputGateway {

    private final CreateEmployeeOutputGateway createEmployeeOutputGateway;

    public CreateEmployeeUseCase(CreateEmployeeOutputGateway createEmployeeOutputGateway) {
        this.createEmployeeOutputGateway = createEmployeeOutputGateway;
    }

    @Override
    public Employee create(Employee employee) {
        checkEmployee(employee);
        return createEmployeeOutputGateway.create(employee);
    }
}
