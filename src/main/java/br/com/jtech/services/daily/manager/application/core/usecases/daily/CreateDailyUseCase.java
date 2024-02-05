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
import br.com.jtech.services.daily.manager.application.core.domains.daily.Email;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeBadRequestException;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.input.daily.CreateDailyInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.*;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateDailyUseCase implements CreateDailyInputGateway {
    private final CreateDailyOutputGateway createDailyOutputGateway;

    private final FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;

    private final FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    private final FindOpenTasksBySquadOutputGateway findOpenTasksBySquadOutputGateway;

    private final FindOpenBlockersBySquadOutputGateway findOpenBlockersBySquadOutputGateway;

    public CreateDailyUseCase(CreateDailyOutputGateway createDailyOutputGateway,
                              FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway,
                              FindSquadByIdOutputGateway findSquadByIdOutputGateway,
                              FindOpenTasksBySquadOutputGateway findOpenTasksBySquadOutputGateway,
                              FindOpenBlockersBySquadOutputGateway findOpenBlockersBySquadOutputGateway) {
        this.createDailyOutputGateway = createDailyOutputGateway;
        this.findEmployeeByEmailOutputGateway = findEmployeeByEmailOutputGateway;
        this.findSquadByIdOutputGateway = findSquadByIdOutputGateway;
        this.findOpenTasksBySquadOutputGateway = findOpenTasksBySquadOutputGateway;
        this.findOpenBlockersBySquadOutputGateway = findOpenBlockersBySquadOutputGateway;
        ;
    }

    public Optional<Daily> create(Daily daily) {
        var employee = getEmployee(daily.getAuthor());
        var squad = getSquad(daily.getSquad());
        daily.setAuthor(employee);
        daily.setSquad(squad);
        daily.setTasks(findOpenTasksBySquadOutputGateway.findBySquad(squad.getName()));
        daily.setBlockers(findOpenBlockersBySquadOutputGateway.findBySquad(squad.getName()));
        return createDailyOutputGateway.create(daily);
    }

    private Employee getEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee is required!");
        final String email = Optional.ofNullable(employee.getEmail())
                .orElseThrow(() -> new EmployeeBadRequestException("Email is invalid!"));
        return findEmployeeByEmailOutputGateway.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found!"));
    }

    private Squad getSquad(Squad squad) {
        Objects.requireNonNull(squad, "Squad is required!");
        final UUID id = Optional.ofNullable(squad.getId())
                .orElseThrow(() -> new SquadNotFoundException("Squad is invalid!"));
        return findSquadByIdOutputGateway.findById(id)
                .orElseThrow(() -> new SquadNotFoundException("Squad not found!"));
    }
}