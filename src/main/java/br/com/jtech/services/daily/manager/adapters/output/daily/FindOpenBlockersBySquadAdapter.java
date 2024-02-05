package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Blocker;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindOpenBlockersBySquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindOpenBlockersBySquadAdapter implements FindOpenBlockersBySquadOutputGateway {

    private final DailyRepository repository;
    @Override
    public List<Blocker> findBySquad(String squad) {
        final var daily = repository.findTopBySquad_NameOrderByCreateAtDesc(squad);
        if (!hasBlockers(daily)) {
            return List.of();
        }
        return daily.get().getBlockers().stream().map(Blocker::fromDocument).filter(blocker -> blocker.isOpen()).toList();
    }

    private static boolean hasBlockers(Optional<DailyDocument> daily) {
        return daily.isPresent() && daily.get().getBlockers() != null && !daily.get().getBlockers().isEmpty();
    }
}
