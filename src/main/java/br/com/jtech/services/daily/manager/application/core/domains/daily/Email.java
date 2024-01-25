package br.com.jtech.services.daily.manager.application.core.domains.daily;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @Builder.Default
    private String from = "daily-manager@jtech.com.br";
    private String to;
    private String subject;
    private String body;

}
