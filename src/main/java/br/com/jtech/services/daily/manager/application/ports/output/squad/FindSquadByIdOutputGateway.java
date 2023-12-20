package br.com.jtech.services.daily.manager.application.ports.output.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;

import java.util.Optional;
import java.util.UUID;

public interface FindSquadByIdOutputGateway {
    Optional<Squad> findById(UUID squadId);
}
