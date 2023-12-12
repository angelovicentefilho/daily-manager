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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindSquadByIdUseCaseTest {

    @Mock
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @InjectMocks
    private FindSquadByIdUseCase findSquadByIdUseCase;

    private Squad squad;
    private String validSquadId;
    private String invalidSquadId;

    @BeforeEach
    public void setUp() {
        validSquadId = UUID.randomUUID().toString();
        invalidSquadId = "invalid-id";
        squad = Squad.builder().id(UUID.randomUUID()).build();
    }

    @Test
    public void testFindByIdWhenValidSquadIdThenReturnSquad() {
        when(findSquadByIdOutputGateway.findById(GenId.newUuid(validSquadId))).thenReturn(squad);

        Squad result = findSquadByIdUseCase.findById(validSquadId);

        verify(findSquadByIdOutputGateway, times(1)).findById(GenId.newUuid(validSquadId));
        assertEquals(squad, result);
    }

    @Test
    public void testFindByIdWhenInvalidSquadIdThenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> findSquadByIdUseCase.findById(invalidSquadId));
    }
}