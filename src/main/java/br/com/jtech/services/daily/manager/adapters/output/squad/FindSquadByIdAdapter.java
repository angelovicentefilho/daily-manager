package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static br.com.jtech.services.daily.manager.application.core.domains.squad.Squad.fromDocument;

@Component
@RequiredArgsConstructor
public class FindSquadByIdAdapter implements FindSquadByIdOutputGateway {

    private final SquadRepository repository;

    @Override
    public Squad findById(UUID uuid) {
        var squadDocument = repository.findById(uuid).orElse(null);
        return fromDocument(squadDocument);
    }
}
