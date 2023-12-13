package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.output.squad.DeleteSquadOuputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSquadUseCaseTest {

    @Mock
    private DeleteSquadOuputGateway deleteSquadOuputGateway;
    @Mock
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @InjectMocks
    private DeleteSquadUseCase deleteSquadUseCase;

    private Squad squad;

    @BeforeEach
    void setUp() {
        squad = new Squad(UUID.randomUUID(), "Squad 1", "Squad 1 description", List.of(), 10, true);
    }

    @Test
    void testDeleteByIdWhenSquadIsFoundThenDeleteSuccessfully() {
        when(findSquadByIdOutputGateway.findById(any(UUID.class))).thenReturn(Optional.of(squad));
        deleteSquadUseCase.deleteById(squad.getId().toString());
        verify(deleteSquadOuputGateway).deleteById(squad.getId());
    }

    @Test
    void testDeleteByIdWhenSquadIsNotFoundThenDoNothing() {
        when(findSquadByIdOutputGateway.findById(any(UUID.class)))
                .thenThrow(new SquadNotFoundException("Squad not found!"));
        assertThrows(SquadNotFoundException.class, () -> deleteSquadUseCase.deleteById(UUID.randomUUID().toString()));
        verify(deleteSquadOuputGateway, never()).deleteById(any(UUID.class));
    }

    @Test
    void testDeleteAll() {
        deleteSquadUseCase.deleteAll();
        verify(deleteSquadOuputGateway).deleteAll();
    }
}
