
/*
 *  @(#)ManagerController.java
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
package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse;
import br.com.jtech.services.daily.manager.application.ports.input.daily.CreateDailyInputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.Https;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.jtech.services.daily.manager.application.core.domains.daily.Daily.fromRequest;


@RestController
@RequestMapping("/api/v1/dailies")
@RequiredArgsConstructor
public class CreateDailyController {

    private final CreateDailyInputGateway createDailyInputGateway;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DailyResponse> create(@RequestBody @Valid DailyRequest request) {
        return createDailyInputGateway.create(fromRequest(request))
                .map(DailyResponse::fromDomain)
                .map(Https::CREATED)
                .orElse(ResponseEntity.badRequest().build());
    }
}