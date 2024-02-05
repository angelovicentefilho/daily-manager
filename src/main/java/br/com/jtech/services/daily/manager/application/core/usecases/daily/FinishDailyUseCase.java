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
import br.com.jtech.services.daily.manager.application.ports.input.daily.FinishDailyInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FinishDailyOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.SendEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class FinishDailyUseCase implements FinishDailyInputGateway {

    public static final String TASK_ASSIGN_SUBJECT = "Task assigned to you!";
    public static final String TASK_ASSIGN_MESSAGE = "The following task has been assigned to you: \n";
    private final FinishDailyOutputGateway finishDailyOutputGateway;

    private final FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;

    private final FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    private final SendEmailOutputGateway sendEmailOutputGateway;

    public FinishDailyUseCase(FinishDailyOutputGateway finishDailyOutputGateway,
                              FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway,
                              FindSquadByIdOutputGateway findSquadByIdOutputGateway,
                              SendEmailOutputGateway sendEmailOutputGateway) {
        this.finishDailyOutputGateway = finishDailyOutputGateway;
        this.findEmployeeByEmailOutputGateway = findEmployeeByEmailOutputGateway;
        this.findSquadByIdOutputGateway = findSquadByIdOutputGateway;
        this.sendEmailOutputGateway = sendEmailOutputGateway;
    }

    @Override
    public Optional<Daily> finish(Daily daily) {
        final var employee = getEmployee(daily.getAuthor());
        final var squad = getSquad(daily.getSquad());
        daily.setAuthor(employee);
        daily.setSquad(squad);
        if (daily.getFinishedAt() == null) {
            daily.setFinishedAt(LocalDateTime.now());
        }
        validateTasks(daily);
        final var finishedDaily = finishDailyOutputGateway.finish(daily);
        if (finishedDaily.isPresent()) {
            sendEmail(finishedDaily.get());
        }
        return finishedDaily;
    }

    private void sendEmail(final Daily daily) {
        if (daily.getTasks() == null || daily.getTasks().isEmpty()) {
            return;
        }
        daily.getTasks().forEach(task -> {
            final Email email = Email.builder()
                    .to(task.getAssignee().getEmail())
                    .subject(TASK_ASSIGN_SUBJECT)
                    .body(TASK_ASSIGN_MESSAGE + task).build();
            sendEmailOutputGateway.send(email);
        });
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