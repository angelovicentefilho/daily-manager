package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindOpenTasksBySquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindOpenTasksBySquadAdapter implements FindOpenTasksBySquadOutputGateway {

    private final DailyRepository repository;

    @Override
    public List<Task> findBySquad(String squad) {
        final var daily = repository.findTopBySquad_NameOrderByCreateAtDesc(squad);
        if (!hasTasks(daily)) {
            return List.of();
        }
        return daily.get().getTasks().stream().map(Task::fromDocument).filter(task -> task.isOpen()).toList();
    }

    private boolean hasTasks(Optional<DailyDocument> daily) {
        return daily.isPresent() && daily.get().getTasks() != null && !daily.get().getTasks().isEmpty();
    }
}
