package br.com.jtech.services.daily.manager.application.ports.output.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

public interface FindEmployeeByUsernameOutputGateway {
    Employee findByUsername(String username);
}
