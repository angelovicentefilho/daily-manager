package br.com.jtech.services.daily.manager.application.core.domains.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.BlockerRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.BlockerDocument;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Blocker {

    private UUID id;
    private String descriptor;
    private String resolution;
    private String impact;
    private String owner;
    private StatusBlocker status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime timeEstimate;
    private List<String> labels;
    private List<String> links;


    public static List<Blocker> fromDocuments(List<BlockerDocument> blockers) {
        return null;
    }

    public static List<Blocker> fromRequest(List<BlockerRequest> blockers) {
        return null;
    }

    public static List<BlockerDocument> toDocuments(List<Blocker> blockers) {
        return null;
    }
}
