package br.com.jtech.services.daily.manager.application.ports.input.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;

public interface FindSquadByIdInputGateway {

    Squad findById(String squadId);
}
