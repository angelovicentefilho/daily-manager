package br.com.jtech.services.daily.manager.application.core.domains.authentication;

import br.com.jtech.services.daily.manager.adapters.input.protocols.authentication.AuthenticationRequest;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DailyAuthentication {

    private UUID id;
    private String email;
    private String password;

    private String token;

    public static DailyAuthentication fromRequest(AuthenticationRequest request) {
        return DailyAuthentication.builder()
                .id(GenId.newUuid())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
