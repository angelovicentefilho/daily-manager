package br.com.jtech.services.daily.manager.application.ports.output.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Email;

public interface SendEmailOutputGateway {
    void send(Email email);
}
