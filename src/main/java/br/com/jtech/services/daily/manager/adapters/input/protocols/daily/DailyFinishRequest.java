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

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * class DailyFinishRequest
 * <p>
 * user filipe.soares@jtech.corp
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyFinishRequest implements Serializable {
    @NotBlank(message = "Id cannot be empty!")
    private String id;
    @NotBlank(message = "Author email cannot be empty!")
    @Email(message = "Author email is not valid!")
    private String authorEmail;
    private String summary;
    private LocalDateTime finishedAt;
    private List<TaskRequest> tasks;
    private List<BlockerRequest> blockers;
}