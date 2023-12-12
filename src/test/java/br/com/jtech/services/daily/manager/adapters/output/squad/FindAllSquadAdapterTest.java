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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FindAllSquadAdapterTest {

    @Mock
    private SquadRepository repository;

    @InjectMocks
    private FindAllSquadAdapter findAllSquadAdapter;

    @Test
    void testFindAllSquads() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        String name1 = "Squad 1";
        String name2 = "Squad 2";
        var squadDocument1 = SquadDocument.builder()
                .id(id1)
                .name(name1)
                .build();
        var squadDocument2 = SquadDocument.builder()
                .id(id2)
                .name(name2)
                .build();
        List<SquadDocument> mockSquadDocuments = Arrays.asList(squadDocument1, squadDocument2);
        when(repository.findAll()).thenReturn(mockSquadDocuments);

        List<Squad> foundSquads = findAllSquadAdapter.findAll();
        verify(repository, times(1)).findAll();

        assertEquals(2, foundSquads.size());
        assertEquals(id1, foundSquads.get(0).getId());
        assertEquals(name1, foundSquads.get(0).getName());
        assertEquals(id2, foundSquads.get(1).getId());
        assertEquals(name2, foundSquads.get(1).getName());
    }
}