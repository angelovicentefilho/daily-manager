package br.com.jtech.services.daily.manager.application.ports.output.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;

import java.util.List;

public interface FindOpenTasksBySquadOutputGateway {

    List<Task> findBySquad(final String squad);
}
