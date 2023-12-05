/*
*  @(#)ManagerInputGateway.java
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
package br.com.jtech.services.daily.manager.application.ports.input.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;

/**
* class ManagerInputGateway 
* 
* user angelo.vicente@jtech.corp 
*/
public interface CreateDailyInputGateway {
    Daily create(Daily daily);
}