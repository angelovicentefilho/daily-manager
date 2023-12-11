package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.UpdateSquadOuputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.jtech.services.daily.manager.application.core.domains.squad.Squad.fromDocument;

@Service
@RequiredArgsConstructor
public class UpdateSquadAdapter implements UpdateSquadOuputGateway {

    private final SquadRepository repository;

    @Override
    public Squad update(Squad squad) {
        return fromDocument(repository.save(squad.toDocument()));
    }
}
