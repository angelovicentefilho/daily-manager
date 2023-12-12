package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.input.squad.DeleteSquadInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.DeleteSquadOuputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;

import static br.com.jtech.services.daily.manager.application.core.validators.SquadValidator.validateId;

public class DeleteSquadUseCase implements DeleteSquadInputGateway {

    private final DeleteSquadOuputGateway deleteSquadOuputGateway;
    private final FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    public DeleteSquadUseCase(DeleteSquadOuputGateway deleteSquadOuputGateway,
                              FindSquadByIdOutputGateway findSquadByIdOutputGateway) {
        this.deleteSquadOuputGateway = deleteSquadOuputGateway;
        this.findSquadByIdOutputGateway = findSquadByIdOutputGateway;
    }

    @Override
    public void deleteById(String id) {
        validateId(id);
        var squad = findSquadByIdOutputGateway.findById(GenId.newUuid(id));
        squad.ifPresent(value -> deleteSquadOuputGateway.deleteById(value.getId()));
    }

    @Override
    public void deleteAll() {
        deleteSquadOuputGateway.deleteAll();
    }
}
