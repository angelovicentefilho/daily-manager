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
import br.com.jtech.services.daily.manager.application.ports.output.daily.CreateDailyOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.SendEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateDailyUseCase implements CreateDailyInputGateway {

    public static final String DAILY_CREATED = "Daily Created";
    private final CreateDailyOutputGateway createDailyOutputGateway;

    private final FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;

    private final FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    private final SendEmailOutputGateway sendEmailOutputGateway;

    public CreateDailyUseCase(CreateDailyOutputGateway createDailyOutputGateway,
                              FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway,
                              FindSquadByIdOutputGateway findSquadByIdOutputGateway,
                              SendEmailOutputGateway sendEmailOutputGateway) {
        this.createDailyOutputGateway = createDailyOutputGateway;
        this.findEmployeeByUsernameOutputGateway = findEmployeeByUsernameOutputGateway;
        this.findSquadByIdOutputGateway = findSquadByIdOutputGateway;
        this.sendEmailOutputGateway = sendEmailOutputGateway;
    }

    public Optional<Daily> create(Daily daily) {
        var employee = getEmployee(daily.getAuthor());
        var squad = getSquad(daily.getSquad());
        daily.setAuthor(employee);
        daily.setSquad(squad);
        validateTasks(daily);
        var dailyCreated = createDailyOutputGateway.create(daily);
        if (dailyCreated.isPresent()) {
            sendEmail(dailyCreated.get());
        }
        return dailyCreated;
    }

    private void sendEmail(final Daily daily) {
        var emails = daily.getTasks()
                .stream()
                .map(task -> task.getAssignee().getEmail()).collect(Collectors.toSet());
        emails.add(daily.getAuthor().getEmail());
        final String body = daily.getTasks()
                .stream()
                .reduce("", (acc, task) -> acc + task.toString() + "\n", String::concat);
        final Email email = Email.builder().to(String.join(";", emails)).subject(DAILY_CREATED).body(body).build();
        sendEmailOutputGateway.send(email);
    }

    private void validateTasks(Daily daily) {
        if (daily.getTasks() != null && !daily.getTasks().isEmpty()) {
            for (var task : daily.getTasks()) {
                var assignee = getEmployee(task.getAssignee());
                task.setAssignee(assignee);
                task.setId(GenId.newUuid(task.getId()));
            }
        }
    }

    private Employee getEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee is required!");
        final String username = Optional.ofNullable(employee.getUsername())
                .orElseThrow(() -> new EmployeeBadRequestException("Username is invalid!"));
        return findEmployeeByUsernameOutputGateway.findByUsername(username)
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