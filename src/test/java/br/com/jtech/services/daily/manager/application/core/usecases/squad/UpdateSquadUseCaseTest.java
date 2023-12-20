package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.UpdateSquadOuputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateSquadUseCaseTest {

    @Mock
    private UpdateSquadOuputGateway updateSquadOutputGateway;

    private UpdateSquadUseCase updateSquadUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateSquadUseCase = new UpdateSquadUseCase(updateSquadOutputGateway);
    }

    @Test
    void testUpdateWhenSquadNotNullThenReturnUpdatedSquad() {
        var squad = Squad.builder()
                .id(UUID.randomUUID())
                .name("Squad 1")
                .description("Squad 1 description")
                .maxCapacity(10)
                .isPublic(true)
                .build();
        when(updateSquadOutputGateway.update(squad)).thenReturn(Optional.of(squad));
        var updatedSquad = updateSquadUseCase.update(squad).get();
        assertEquals(squad, updatedSquad);
        verify(updateSquadOutputGateway).update(squad);
    }
}