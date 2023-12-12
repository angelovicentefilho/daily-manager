package br.com.jtech.services.daily.manager.application.ports.input.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;

import java.util.Optional;

public interface UpdateSquadInputGateway {
    Optional<Squad> update(Squad request);
}
