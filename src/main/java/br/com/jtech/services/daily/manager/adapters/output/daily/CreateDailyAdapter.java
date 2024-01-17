/*
 *  @(#)ManagerAdapter.java
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
package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CreateDailyOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.jtech.services.daily.manager.application.core.domains.daily.Daily.fromDocument;
import static java.util.Optional.of;

/**
 * class CreateDailyAdapter
 * <p>
 * user angelo.vicente@jtech.corp
 */
@Component
@RequiredArgsConstructor
public class CreateDailyAdapter implements CreateDailyOutputGateway {

    private final DailyRepository repository;

    @Override
    public Optional<Daily> create(Daily daily) {
        return of(fromDocument(this.repository.save(daily.toDocument())));
    }

}