/*
 *  @(#)Daily.java
 *
 *  Copyright (c) J-Tech Solucoes em Informatica.
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of J-Tech.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with J-Tech.
 *
 */
package br.com.jtech.services.daily.manager.application.core.domains.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyCreateRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyFinishRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


/**
 * class Daily
 * <p>
 * user angelo.vicente@jtech.corp
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Daily {

    private UUID id;
    private Squad squad;
    private Employee author;
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private List<Task> tasks;
    private List<Blocker> blockers;

    public static List<Daily> fromDocuments(List<DailyDocument> documents) {
        return documents.stream().map(Daily::fromDocument).toList();
    }

    public DailyDocument toDocument() {
        return DailyDocument.builder()
                .id(getId())
                .summary(getSummary())
                .author(getAuthor().toDocument())
                .squad(getSquad().toDocument())
                .createdAt(getCreatedAt())
                .finishedAt(getFinishedAt())
                .tasks(Task.toDocuments(getTasks()))
                .blockers(Blocker.toDocuments(getBlockers()))
                .build();
    }

    public static Daily fromDocument(DailyDocument document) {
        return Daily.builder()
                .id(document.getId())
                .author(Employee.fromDocument(document.getAuthor()))
                .squad(Squad.fromDocument(document.getSquad()))
                .createdAt(document.getCreatedAt())
                .summary(document.getSummary())
                .finishedAt(document.getFinishedAt())
                .tasks(Task.fromDocuments(document.getTasks()))
                .blockers(Blocker.fromDocuments(document.getBlockers()))
                .build();
    }

    public static Daily fromRequest(DailyRequest request) {
        var daily = Daily.builder()
                .squad(Squad.builder().id(UUID.fromString(request.getSquadId())).build())
                .author(Employee.newEmployee(request.getAuthorEmail()))
                .summary(request.getSummary())
                .createdAt(request.getCreatedAt())
                .tasks(Task.fromRequests(request.getTasks()))
                .blockers(Blocker.fromRequests(request.getBlockers()))
                .build();
        daily.setId(GenId.newUuid(request.getId()));
        return daily;
    }

    public static Daily fromFinishRequest(DailyFinishRequest request) {
        var daily = Daily.builder()
                .author(Employee.newEmployee(request.getAuthorEmail()))
                .summary(request.getSummary())
                .tasks(Task.fromRequests(request.getTasks()))
                .blockers(Blocker.fromRequests(request.getBlockers()))
                .finishedAt(request.getFinishedAt())
                .id(GenId.newUuid(request.getId()))
                .build();
        return daily;
    }

    public static Daily fromCreateRequest(DailyCreateRequest request) {
        var daily = Daily.builder()
                .squad(newSquad(request))
                .author(Employee.newEmployee(request.getAuthorEmail()))
                .summary(request.getSummary())
                .build();
        daily.setId(GenId.newUuid());
        return daily;
    }

    private static Squad newSquad(DailyCreateRequest request) {
        return Squad.builder().id(UUID.fromString(request.getSquadId())).build();
    }
}