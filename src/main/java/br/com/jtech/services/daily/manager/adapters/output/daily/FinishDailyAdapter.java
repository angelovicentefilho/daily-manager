package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FinishDailyOutputGateway;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class FinishDailyAdapter implements FinishDailyOutputGateway {

    private final DailyRepository repository;

    public FinishDailyAdapter(DailyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Daily> finish(Daily daily) {
        if (Objects.isNull(daily)) {
            return Optional.empty();
        }
        var document = daily.toDocument();
        document.setUpdatedAt(LocalDateTime.now());
        return Optional.of(Daily.fromDocument(repository.save(document)));
    }
}
