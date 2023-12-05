
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse.fromDomain;
import static br.com.jtech.services.daily.manager.application.core.domains.daily.Daily.fromRequest;
import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v1/dailies")
@RequiredArgsConstructor
public class CreateDailyController {

    private final CreateDailyInputGateway createDailyInputGateway;

    @PostMapping
    public ResponseEntity<DailyResponse> create(@RequestBody DailyRequest request) {
        var daily = fromDomain(createDailyInputGateway.create(fromRequest(request)));
        return ResponseEntity.status(CREATED).body(daily);
    }
}