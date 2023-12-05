package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockerResponse implements Serializable {
    private String id;
    private String descriptor;
    private String resolution;
    private String impact;
    private String owner;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime timeEstimate;
    private List<String> labels;
    private List<String> links;


    private List<BlockerResponse> blockers;
}
