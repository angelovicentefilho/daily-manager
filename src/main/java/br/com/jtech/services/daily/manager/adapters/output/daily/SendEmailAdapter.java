package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Email;
import br.com.jtech.services.daily.manager.application.ports.output.daily.SendEmailOutputGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SendEmailAdapter implements SendEmailOutputGateway {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(Email email) {
        try {
            log.debug(">>> Sending email to: '{}'", email.getTo());
            var message = new SimpleMailMessage();
            message.setFrom(email.getFrom());
            message.setTo(email.getTo().split(";"));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Cannot send message: ", e);
        }
    }

}
