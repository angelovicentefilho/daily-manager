package br.com.jtech.services.daily.manager.application.ports.output.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

public interface CreateEmployeeOutputGateway {
    Employee create(Employee employee);
}
