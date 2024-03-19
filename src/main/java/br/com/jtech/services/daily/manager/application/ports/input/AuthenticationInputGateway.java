package br.com.jtech.services.daily.manager.application.ports.input;

import br.com.jtech.services.daily.manager.application.core.domains.authentication.DailyAuthentication;

import java.util.Optional;

public interface AuthenticationInputGateway {
    Optional<DailyAuthentication> authenticate(DailyAuthentication domain);
}
