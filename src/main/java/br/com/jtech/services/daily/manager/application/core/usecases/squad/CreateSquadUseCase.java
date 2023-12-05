package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.input.squad.CreateSquadInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.CreateSquadOutputGateway;

public class CreateSquadUseCase implements CreateSquadInputGateway {

    private final CreateSquadOutputGateway createSquadOutputGateway;

    public CreateSquadUseCase(CreateSquadOutputGateway createSquadOutputGateway) {
        this.createSquadOutputGateway = createSquadOutputGateway;
    }

    @Override
    public Squad create(Squad request) {
        return null;
    }
}
