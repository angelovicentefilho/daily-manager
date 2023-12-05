package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindDailyBySquadAndDateOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FindDailyBySquadAndDateAdapter implements FindDailyBySquadAndDateOutputGateway {

    private final DailyRepository repository;

    @Override
    public Daily findBySquadAndDate(String squad, LocalDateTime dateTime) {
        return Daily.fromDocument(repository.findBySquad_NameAndCreatedAt(squad, dateTime).orElseThrow(() -> {
            throw new IllegalArgumentException("not found");
        }));
    }
}
