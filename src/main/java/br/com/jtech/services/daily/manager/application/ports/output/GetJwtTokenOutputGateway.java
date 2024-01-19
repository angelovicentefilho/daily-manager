package br.com.jtech.services.daily.manager.application.ports.output;

import br.com.jtech.services.daily.manager.application.core.domains.authentication.DailyAuthentication;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;

import java.util.Optional;

public interface GetJwtTokenOutputGateway {
    Optional<DailyAuthentication> generate(Employee employee);
}
