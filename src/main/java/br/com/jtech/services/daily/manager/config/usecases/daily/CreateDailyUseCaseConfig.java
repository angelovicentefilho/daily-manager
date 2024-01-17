/*
*  @(#)ManagerUseCaseConfig.java
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
package br.com.jtech.services.daily.manager.config.usecases.daily;

import br.com.jtech.services.daily.manager.adapters.output.daily.CreateDailyAdapter;
import br.com.jtech.services.daily.manager.adapters.output.employee.FindEmployeeByUsernameAdapter;
import br.com.jtech.services.daily.manager.adapters.output.squad.FindSquadByIdAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.daily.CreateDailyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* class ManagerUseCaseConfig
* 
* user angelo.vicente@jtech.corp
*/
@Configuration
public class CreateDailyUseCaseConfig {

    @Bean
    public CreateDailyUseCase useCase(CreateDailyAdapter createManagerAdapter, FindEmployeeByUsernameAdapter findEmployeeByUsernameAdapter, FindSquadByIdAdapter findSquadByIdAdapter) {
        return new CreateDailyUseCase(createManagerAdapter, findEmployeeByUsernameAdapter, findSquadByIdAdapter);
     }

 }