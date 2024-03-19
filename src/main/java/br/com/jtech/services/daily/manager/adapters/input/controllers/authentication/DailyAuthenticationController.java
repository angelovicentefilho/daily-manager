package br.com.jtech.services.daily.manager.adapters.input.controllers.authentication;

import br.com.jtech.services.daily.manager.adapters.input.protocols.authentication.AuthenticationRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.authentication.AuthenticationResponse;
import br.com.jtech.services.daily.manager.application.core.domains.authentication.DailyAuthentication;
import br.com.jtech.services.daily.manager.application.ports.input.AuthenticationInputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import br.com.jtech.services.daily.manager.config.infra.utils.Https;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@JtechRestController("/api/auth")
@RequiredArgsConstructor
public class DailyAuthenticationController {

    private final AuthenticationInputGateway authenticationInputGateway;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationInputGateway.authenticate(DailyAuthentication.fromRequest(request))
                .map(AuthenticationResponse::fromDomain)
                .map(Https::OK)
                .orElse(ResponseEntity.badRequest().build());
    }

}
