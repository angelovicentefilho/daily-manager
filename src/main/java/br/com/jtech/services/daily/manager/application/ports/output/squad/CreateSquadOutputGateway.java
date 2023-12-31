package br.com.jtech.services.daily.manager.application.ports.output.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;

import java.util.Optional;

public interface CreateSquadOutputGateway {

    Optional<Squad> create(Squad request);
}
