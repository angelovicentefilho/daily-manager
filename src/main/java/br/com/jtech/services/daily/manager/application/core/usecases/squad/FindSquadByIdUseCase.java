package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.input.squad.FindSquadByIdInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;

import java.util.Optional;

import static br.com.jtech.services.daily.manager.application.core.validators.SquadValidator.validateId;

public class FindSquadByIdUseCase implements FindSquadByIdInputGateway {
    private final FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    public FindSquadByIdUseCase(FindSquadByIdOutputGateway findSquadByIdOutputGateway) {
        this.findSquadByIdOutputGateway = findSquadByIdOutputGateway;
    }

    @Override
    public Optional<Squad> findById(String squadId) {
        validateId(squadId);
        return Optional.of(findSquadByIdOutputGateway.findById(GenId.newUuid(squadId)).orElseThrow(() -> new SquadNotFoundException("Squad not found!")));
    }
}
