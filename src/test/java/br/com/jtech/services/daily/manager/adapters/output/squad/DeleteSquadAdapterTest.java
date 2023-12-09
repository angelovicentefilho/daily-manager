package br.com.jtech.services.daily.manager.adapters.output.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.SquadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteSquadAdapterTest {

    @Mock
    private SquadRepository squadRepository;

    @InjectMocks
    private DeleteSquadAdapter deleteSquadAdapter;

    @Test
    void testDeleteByIdWhenCalledThenRepositoryDeleteByIdCalledWithGivenUUID() {
        UUID id = UUID.randomUUID();
        doNothing().when(squadRepository).deleteById(id);
        deleteSquadAdapter.deleteById(id);
        verify(squadRepository).deleteById(id);
    }

    @Test
    void testDeleteAllWhenCalledThenRepositoryDeleteAllCalled() {
        doNothing().when(squadRepository).deleteAll();
        deleteSquadAdapter.deleteAll();
        verify(squadRepository).deleteAll();
    }
}