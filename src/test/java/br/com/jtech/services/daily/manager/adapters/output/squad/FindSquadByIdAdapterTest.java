package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindSquadByIdAdapterTest {

    @Mock
    private SquadRepository repository;

    @InjectMocks
    private FindSquadByIdAdapter findSquadByIdAdapter;

    @Test
    void testFindSquadById() {
        UUID id = UUID.randomUUID();
        String name = "Squad 1";
        var squadDocument = SquadDocument.builder()
                .id(id)
                .name(name)
                .build();
        when(repository.findById(id)).thenReturn(Optional.of(squadDocument));

        Squad foundSquad = findSquadByIdAdapter.findById(id).get();
        verify(repository, times(1)).findById(id);

        assertEquals(id, foundSquad.getId());
        assertEquals(name, foundSquad.getName());
    }
}