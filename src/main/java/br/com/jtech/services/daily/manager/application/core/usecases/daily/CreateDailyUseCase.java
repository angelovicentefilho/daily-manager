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
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeBadRequestException;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.input.daily.CreateDailyInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CreateDailyOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;

import java.util.Optional;

public class CreateDailyUseCase implements CreateDailyInputGateway {

    private final CreateDailyOutputGateway createDailyOutputGateway;

    private final FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;

    private final FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    public CreateDailyUseCase(CreateDailyOutputGateway createDailyOutputGateway, FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway, FindSquadByIdOutputGateway findSquadByIdOutputGateway) {
        this.createDailyOutputGateway = createDailyOutputGateway;
        this.findEmployeeByUsernameOutputGateway = findEmployeeByUsernameOutputGateway;
        this.findSquadByIdOutputGateway = findSquadByIdOutputGateway;
    }

    public Optional<Daily> create(Daily daily) {
        var employee = getEmployee(daily.getAuthor());
        var squad = getSquad(daily.getSquad());
        daily.setAuthor(employee);
        daily.setSquad(squad);
        validateTasks(daily);
        return createDailyOutputGateway.create(daily);
    }

    private void validateTasks(Daily daily) {
        if (daily.getTasks() != null && !daily.getTasks().isEmpty()) {
            for (var task : daily.getTasks()) {
                var assignee = getEmployee(task.getAssignee());
                task.setAssignee(assignee);
                if (task.getId() == null) {
                    task.setId(GenId.newUuid());
                }
            }
        }
    }

    private Employee getEmployee(Employee employee) {
        if (employee == null || employee.getUsername() == null) {
            throw new EmployeeBadRequestException("Author is invalid!");
        }
        var employeeFound = findEmployeeByUsernameOutputGateway.findByUsername(employee.getUsername());
        if (employeeFound.isEmpty()) {
            throw new EmployeeNotFoundException("Author not found!");
        }
        return employeeFound.get();
    }

    private Squad getSquad(Squad squad) {
        if (squad == null || squad.getId() == null) {
            throw new IllegalArgumentException("Squad is invalid!");
        }
        var squadFound = findSquadByIdOutputGateway.findById(squad.getId());
        if (squadFound.isEmpty()) {
            throw new SquadNotFoundException("Squad not found!");
        }
        return squadFound.get();
    }
}