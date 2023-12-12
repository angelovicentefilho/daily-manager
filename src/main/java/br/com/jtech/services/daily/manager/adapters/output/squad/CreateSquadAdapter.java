package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.CreateSquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateSquadAdapter implements CreateSquadOutputGateway {

    private final SquadRepository repository;

    @Override
    public Optional<Squad> create(Squad request) {
        var document = this.repository.save(request.toDocument());
        return Optional.of(Squad.fromDocument(document));
    }
}
