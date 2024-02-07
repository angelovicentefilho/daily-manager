package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CheckOpenDailyExistenceOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CheckOpenDailyExistenceAdapter implements CheckOpenDailyExistenceOutputGateway {

    private final DailyRepository repository;

    @Override
    public boolean exists(final UUID dailyId) {
        return repository.existsByIdAndFinishedAtIsNull(dailyId);
    }
}
