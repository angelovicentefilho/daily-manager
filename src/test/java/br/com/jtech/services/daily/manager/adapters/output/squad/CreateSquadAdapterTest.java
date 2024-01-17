package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSquadAdapterTest {

    @Mock
    private SquadRepository repository;

    @InjectMocks
    private CreateSquadAdapter createSquadAdapter;

    @Test
    void testCreateSquad() {
        var id = UUID.randomUUID();
        String name = "Squad 1";
        String description = "Squad 1 description";
        int maxCapacity = 10;
        boolean isPublic = true;
        List<Employee> members = List.of();
        var mockSquad = Squad.builder()
                .id(id)
                .name(name)
                .description(description)
                .members(members)
                .maxCapacity(maxCapacity)
                .isPublic(isPublic)
                .build();
        var mockSquadDocument = SquadDocument.builder()
                .id(id)
                .name(name)
                .description(description)
                .members(members)
                .maxCapacity(maxCapacity)
                .isPublic(isPublic)
                .build();
        when(repository.save(any(SquadDocument.class))).thenReturn(mockSquadDocument);

        var createdSquad = createSquadAdapter.create(mockSquad).get();

        verify(repository, times(1)).save(any(SquadDocument.class));
        assertEquals(id, createdSquad.getId());
        assertEquals(name, createdSquad.getName());
        assertEquals(description, createdSquad.getDescription());
        assertEquals(maxCapacity, createdSquad.getMaxCapacity());
        assertEquals(isPublic, createdSquad.getIsPublic());
    }
}