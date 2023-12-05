package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindDailyBySquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.jtech.services.daily.manager.application.core.domains.daily.Daily.fromDocuments;

@Component
@RequiredArgsConstructor
public class FindDailyBySquadAdapter implements FindDailyBySquadOutputGateway {

    private final DailyRepository repository;

    @Override
    public List<Daily> findBySquad(String squad) {
        var documents = repository.findBySquad_Name(squad);
        return fromDocuments(documents);
    }
}
