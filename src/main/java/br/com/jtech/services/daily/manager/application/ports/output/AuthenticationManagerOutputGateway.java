package br.com.jtech.services.daily.manager.application.ports.output;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationManagerOutputGateway {
    void authenticate(UsernamePasswordAuthenticationToken token);
}
