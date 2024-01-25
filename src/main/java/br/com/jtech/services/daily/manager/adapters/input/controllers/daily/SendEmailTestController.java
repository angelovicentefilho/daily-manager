package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Email;
import br.com.jtech.services.daily.manager.application.ports.output.daily.SendEmailOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@JtechRestController("/emails")
public class SendEmailTestController {

    private final SendEmailOutputGateway sendEmailOutputGateway;

    @GetMapping
    public ResponseEntity<String> send() {
        var email = new Email();
        email.setBody("Meu Email");
        email.setTo("joaquin@email.com");
        email.setSubject("Test");
        sendEmailOutputGateway.send(email);
        return ResponseEntity.ok("Sent");
    }

}
