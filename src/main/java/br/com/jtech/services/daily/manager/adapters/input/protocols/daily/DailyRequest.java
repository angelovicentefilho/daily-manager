/*
 *  @(#)DailyRequest.java
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

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
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

/**
 * class DailyRequest
 * <p>
 * user angelo.vicente@jtech.corp
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyRequest implements Serializable {
    private String id;
    @NotBlank(message = "Squad cannot be null!")
    private String squadId;
    @NotBlank(message = "Author cannot be null!")
    private String authorUsername;
    private String summary;
    private LocalDateTime createdAt;
    private List<TaskRequest> tasks;
    private List<BlockerRequest> blockers;

    private List<DailyRequest> requests;
}