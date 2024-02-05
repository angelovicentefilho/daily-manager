package br.com.jtech.services.daily.manager.application.ports.output.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Blocker;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;

import java.util.List;

public interface FindOpenBlockersBySquadOutputGateway {

    List<Blocker> findBySquad(final String squad);
}
