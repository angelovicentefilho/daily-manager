package br.com.jtech.services.daily.manager.adapters.output.authentication;

import br.com.jtech.services.daily.manager.application.core.domains.authentication.DailyAuthentication;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.GetJwtTokenOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.security.DailyJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetJwtTokenAdapter implements GetJwtTokenOutputGateway {

    private final DailyJwtService service;

    @Override
    public Optional<DailyAuthentication> generate(Employee employee) {
        var token = service.generateToken(employee);
        return Optional.of(DailyAuthentication.builder()
                .id(employee.getId())
                .token(token)
                .build());
    }
}
