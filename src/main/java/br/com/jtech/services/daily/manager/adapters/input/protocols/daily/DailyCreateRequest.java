package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * class DailyCreateRequest
 * <p>
 * user filipe.soares@jtech.corp
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyCreateRequest implements Serializable {
    @NotBlank(message = "Squad cannot be empty!")
    private String squadId;
    @NotBlank(message = "Author email cannot be empty!")
    private String authorEmail;
    private String summary;
}