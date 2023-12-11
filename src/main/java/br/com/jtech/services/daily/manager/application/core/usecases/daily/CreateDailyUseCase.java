/*
 *  @(#)ManagerUseCase.java
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
package br.com.jtech.services.daily.manager.application.core.usecases.daily;


import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.ports.input.daily.CreateDailyInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CreateDailyOutputGateway;

import java.util.Optional;

public class CreateDailyUseCase implements CreateDailyInputGateway {

    private final CreateDailyOutputGateway createDailyOutputGateway;

    public CreateDailyUseCase(CreateDailyOutputGateway createDailyOutputGateway) {
        this.createDailyOutputGateway = createDailyOutputGateway;
    }

    public Optional<Daily> create(Daily daily) {
        return createDailyOutputGateway.create(daily);
    }
}