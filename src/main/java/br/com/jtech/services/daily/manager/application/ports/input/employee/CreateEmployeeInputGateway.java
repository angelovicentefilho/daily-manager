package br.com.jtech.services.daily.manager.application.ports.input.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

public interface CreateEmployeeInputGateway {
    Employee create(Employee employee);
}
