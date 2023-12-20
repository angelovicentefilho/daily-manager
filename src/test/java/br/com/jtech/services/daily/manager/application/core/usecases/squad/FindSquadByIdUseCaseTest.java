package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindSquadByIdUseCaseTest {

    @Mock
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @InjectMocks
    private FindSquadByIdUseCase findSquadByIdUseCase;

    private Squad squad;

    @BeforeEach
    void setUp() {
        squad = Squad.builder().id(UUID.randomUUID()).build();
    }

    @Test
    void testFindByIdWhenValidSquadIdThenReturnSquad() {
        var validSquadId = UUID.randomUUID().toString();
        when(findSquadByIdOutputGateway.findById(GenId.newUuid(validSquadId))).thenReturn(Optional.of(squad));

        Squad result = findSquadByIdUseCase.findById(validSquadId).get();

        verify(findSquadByIdOutputGateway, times(1)).findById(GenId.newUuid(validSquadId));
        assertEquals(squad, result);
    }

    @Test
    void testFindByIdWhenInvalidSquadIdThenThrowException() {
        var invalidSquadId = "invalid-id";
        assertThrows(IllegalArgumentException.class, () -> findSquadByIdUseCase.findById(invalidSquadId));
    }

    @Test
    void testFindByIdWhenNotFoundSquadIdThenThrowException() {
        var squadId = UUID.randomUUID().toString();
        assertThrows(SquadNotFoundException.class, () -> findSquadByIdUseCase.findById(squadId));
    }
}