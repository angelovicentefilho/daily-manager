package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.UpdateEmployeeInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.UpdateEmployeeOuputGateway;

import static br.com.jtech.services.daily.manager.application.core.validators.EmployeeValidator.validateIdNotNull;

public class UpdateEmployeeUseCase implements UpdateEmployeeInputGateway {

    private final UpdateEmployeeOuputGateway updateEmployeeOuputGateway;

    public UpdateEmployeeUseCase(UpdateEmployeeOuputGateway updateEmployeeOuputGateway) {
        this.updateEmployeeOuputGateway = updateEmployeeOuputGateway;
    }

    @Override
    public Employee update(Employee domain) {
        validateIdNotNull(domain.getId());
        return updateEmployeeOuputGateway.update(domain);
    }
}
