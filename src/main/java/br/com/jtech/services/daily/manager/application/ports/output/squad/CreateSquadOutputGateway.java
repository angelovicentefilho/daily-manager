package br.com.jtech.services.daily.manager.application.ports.output.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;

public interface CreateSquadOutputGateway {

    Squad create(Squad request);
}
