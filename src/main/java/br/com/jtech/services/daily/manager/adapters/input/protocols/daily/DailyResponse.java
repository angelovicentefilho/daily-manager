/*
 *  @(#)DailyResponse.java
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
package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * class DailyResponse
 * <p>
 * user angelo.vicente@jtech.corp
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyResponse implements Serializable {

    private String id;
    private String summary;
    private SquadResponse squad;
    private EmployeeResponse author;
    private LocalDateTime createdAt;
    private List<TaskResponse> tasks;
    private List<BlockerResponse> blockers;

    private List<DailyResponse> dailies;

    public static DailyResponse of(Daily daily) {
        return DailyResponse.builder()
                .id(daily.getId().toString())
                .summary(daily.getSummary())
                .squad(SquadResponse.fromDomain(daily.getSquad()))
                .author(EmployeeResponse.fromDomain(daily.getAuthor()))
                .build();
    }

    public static DailyResponse of(List<DailyDocument> entities) {
        var list = entities.stream().map(DailyResponse::of).toList();
        return DailyResponse.builder()
                .dailies(list)
                .build();
    }

    public static DailyResponse of(DailyDocument entity) {
        var response = new DailyResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public static DailyResponse fromDomain(Daily daily) {
        var response = new DailyResponse();
        BeanUtils.copyProperties(daily, response);
        response.setId(daily.getId().toString());
        response.setSquad(SquadResponse.fromDomain(daily.getSquad()));
        response.setAuthor(EmployeeResponse.fromDomain(daily.getAuthor()));
        return response;
    }

    public static DailyResponse fromDomains(List<Daily> dailies) {
        var list = dailies.stream().map(DailyResponse::of).toList();
        return DailyResponse.builder().dailies(list).build();
    }
}