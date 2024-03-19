package br.com.jtech.services.daily.manager.adapters.input.protocols.authentication;

import br.com.jtech.services.daily.manager.application.core.domains.authentication.DailyAuthentication;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponse {
    private String id;
    @JsonProperty("token_required")
    private String token;

    public static AuthenticationResponse fromDomain(final DailyAuthentication authentication) {
        return AuthenticationResponse.builder()
                .id(authentication.getId().toString())
                .token(authentication.getToken())
                .build();
    }
}
