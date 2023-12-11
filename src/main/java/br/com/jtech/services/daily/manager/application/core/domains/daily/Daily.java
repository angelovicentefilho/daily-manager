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
                .tasks(Task.fromDocuments(document.getTasks()))
                .blockers(Blocker.fromDocuments(document.getBlockers()))
                .build();
    }

    public static Daily fromRequest(DailyRequest request) {
        return Daily.builder()
                .id(GenId.newUuid(request.getId()))
                .squad(Squad.fromRequest(request.getSquad()))
                .author(Employee.fromRequest(request.getAuthor()))
                .createdAt(request.getCreatedAt())
                .tasks(Task.fromRequests(request.getTasks()))
                .blockers(Blocker.fromRequests(request.getBlockers()))
                .build();
    }
}