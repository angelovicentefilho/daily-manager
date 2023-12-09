package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class FindSquadByIdAdapterTest {

    @Mock
    private SquadRepository repository;

    @InjectMocks
    private FindSquadByIdAdapter findSquadByIdAdapter;

    @Test
    public void testFindSquadById() {
        UUID id = UUID.randomUUID();
        String name = "Squad 1";
        var squadDocument = SquadDocument.builder()
                .id(id)
                .name(name)
                .build();
        when(repository.findById(id)).thenReturn(Optional.of(squadDocument));

        Squad foundSquad = findSquadByIdAdapter.findById(id);
        verify(repository, times(1)).findById(id);

        assertEquals(id, foundSquad.getId());
        assertEquals(name, foundSquad.getName());
    }
}