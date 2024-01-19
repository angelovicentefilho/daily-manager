package br.com.jtech.services.daily.manager.application.core.usecases.authentication;

import br.com.jtech.services.daily.manager.application.core.domains.authentication.DailyAuthentication;
import br.com.jtech.services.daily.manager.application.ports.input.AuthenticationInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.AuthenticationManagerOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.GetJwtTokenOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

public class AuthenticationUseCase implements AuthenticationInputGateway {

    private final AuthenticationManagerOutputGateway authenticationManagerOutputGateway;
    private final FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;
    private final GetJwtTokenOutputGateway getJwtTokenOutputGateway;

    public AuthenticationUseCase(final AuthenticationManagerOutputGateway authenticationManagerOutputGateway,
                                 final FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway,
                                 final GetJwtTokenOutputGateway getJwtTokenOutputGateway) {
        this.authenticationManagerOutputGateway = authenticationManagerOutputGateway;
        this.findEmployeeByEmailOutputGateway = findEmployeeByEmailOutputGateway;
        this.getJwtTokenOutputGateway = getJwtTokenOutputGateway;
    }

    @Override
    public Optional<DailyAuthentication> authenticate(DailyAuthentication domain) {
        authenticationManagerOutputGateway.authenticate(new UsernamePasswordAuthenticationToken(domain.getEmail(), domain.getPassword()));
        var user = findEmployeeByEmailOutputGateway.findByEmail(domain.getEmail());
       if (user.isPresent()) {
           return getJwtTokenOutputGateway.generate(user.get());
       }
       throw new IllegalArgumentException("Error");
    }
}
