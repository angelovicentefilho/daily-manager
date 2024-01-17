package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.input.squad.CreateSquadInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.CreateSquadOutputGateway;

import java.util.Optional;

public class CreateSquadUseCase implements CreateSquadInputGateway {

    private final CreateSquadOutputGateway createSquadOutputGateway;

    public CreateSquadUseCase(CreateSquadOutputGateway createSquadOutputGateway) {
        this.createSquadOutputGateway = createSquadOutputGateway;
    }

    @Override
    public Optional<Squad> create(Squad squad) {
        return createSquadOutputGateway.create(squad);
    }
}
