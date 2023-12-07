package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.CreateSquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSquadAdapter implements CreateSquadOutputGateway {

    private final SquadRepository repository;

    @Override
    public Squad create(Squad request) {
        var document = this.repository.save(request.toDocument());
        return Squad.fromDocument(document);
    }
}
