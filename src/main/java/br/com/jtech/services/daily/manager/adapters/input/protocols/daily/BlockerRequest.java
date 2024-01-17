package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockerRequest implements Serializable {
    private String id;
    @NotBlank(message = "Description cannot be empty!")
    private String descriptor;
    private String resolution;
    @NotBlank(message = "Impact cannot be empty!")
    private String impact;
    @NotBlank(message = "Owner cannot be empty!")
    private String owner;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull(message = "Estimative cannot be null!")
    private LocalDateTime timeEstimate;
    private List<String> labels;
    private List<String> links;


    private List<BlockerRequest> blockers;
}
