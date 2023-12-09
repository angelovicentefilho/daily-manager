package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindAllSquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.jtech.services.daily.manager.application.core.domains.squad.Squad.fromDocuments;

@Component
@RequiredArgsConstructor
public class FindAllSquadAdapter implements FindAllSquadOutputGateway {

    private final SquadRepository repository;
    @Override
    public List<Squad> findAll() {
        return fromDocuments(this.repository.findAll());
    }
}
