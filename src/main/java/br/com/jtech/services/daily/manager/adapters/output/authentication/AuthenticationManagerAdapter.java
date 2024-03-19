package br.com.jtech.services.daily.manager.adapters.output.authentication;

import br.com.jtech.services.daily.manager.application.ports.output.AuthenticationManagerOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationManagerAdapter implements AuthenticationManagerOutputGateway {

    private final AuthenticationManager manager;

    @Override
    public void authenticate(UsernamePasswordAuthenticationToken token) {
        manager.authenticate(token);
    }
}
