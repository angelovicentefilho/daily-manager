package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.input.squad.UpdateSquadInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.UpdateSquadOuputGateway;

import static br.com.jtech.services.daily.manager.application.core.validators.SquadValidator.validateIdNotNull;

public class UpdateSquadUseCase implements UpdateSquadInputGateway {

    private final UpdateSquadOuputGateway updateSquadOuputGateway;

    public UpdateSquadUseCase(UpdateSquadOuputGateway updateSquadOuputGateway) {
        this.updateSquadOuputGateway = updateSquadOuputGateway;
    }

    @Override
    public Squad update(Squad domain) {
        validateIdNotNull(domain.getId());
        return updateSquadOuputGateway.update(domain);
    }
}
