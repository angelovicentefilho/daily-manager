package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindDailyByDateOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindDailyByDateAdapter implements FindDailyByDateOutputGateway {

    private final DailyRepository repository;

    @Override
    public List<Daily> findByDate(LocalDateTime dateTime) {
        var documents = repository.findByCreatedAt(dateTime);
        return Daily.fromDocuments(documents);
    }
}
