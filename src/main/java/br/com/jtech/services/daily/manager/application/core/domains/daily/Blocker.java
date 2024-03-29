package br.com.jtech.services.daily.manager.application.core.domains.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.BlockerRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.BlockerDocument;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import lombok.*;
import org.springframework.beans.BeanUtils;

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

    public boolean isOpen() {
        return StatusBlocker.OPEN.equals(status) || StatusBlocker.IN_PROGRESS.equals(status);
    }

    public static List<Blocker> fromDocuments(List<BlockerDocument> blockers) {
        return blockers.stream().map(Blocker::fromDocument).toList();
    }

    public static Blocker fromDocument(BlockerDocument document) {
        var blocker = new Blocker();
        BeanUtils.copyProperties(document, blocker);
        blocker.setStatus(StatusBlocker.of(document.getStatus()));
        return blocker;
    }

    public static Blocker fromRequest(BlockerRequest request) {
        return Blocker.builder()
                .id(GenId.newUuid(request.getId()))
                .descriptor(request.getDescriptor())
                .impact(request.getImpact())
                .resolution(request.getResolution())
                .owner(request.getOwner())
                .status(StatusBlocker.of(request.getStatus()))
                .timeEstimate(request.getTimeEstimate())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .labels(request.getLabels())
                .links(request.getLinks())
                .build();
    }

    public BlockerDocument toDocument() {
        return BlockerDocument.builder()
                .id(getId())
                .descriptor(getDescriptor())
                .impact(getImpact())
                .resolution(getResolution())
                .owner(getOwner())
                .status(getStatus().name())
                .timeEstimate(getTimeEstimate())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .labels(getLabels())
                .links(getLinks())
                .build();
    }

    public BlockerDocument toDocument(BlockerDocument bloker) {
        return BlockerDocument.builder()
                .id(bloker.getId())
                .descriptor(bloker.getDescriptor())
                .impact(bloker.getImpact())
                .resolution(bloker.getResolution())
                .owner(bloker.getOwner())
                .status(bloker.getStatus())
                .timeEstimate(bloker.getTimeEstimate())
                .createdAt(bloker.getCreatedAt())
                .updatedAt(bloker.getUpdatedAt())
                .labels(bloker.getLabels())
                .links(bloker.getLinks())
                .build();
    }

    public static List<BlockerDocument> toDocuments(List<Blocker> blockers) {
        return blockers.stream().map(Blocker::toDocument).toList();
    }


    public static List<Blocker> fromRequests(List<BlockerRequest> requests) {
        if (requests == null) {
            return List.of();
        }
        return requests.stream().map(Blocker::fromRequest).toList();
    }

}
