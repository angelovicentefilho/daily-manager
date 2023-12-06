package br.com.jtech.services.daily.manager.application.ports.input.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

public interface UpdateEmployeeInputGateway {
    Employee update(Employee request);
}
