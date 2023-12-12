package br.com.jtech.services.daily.manager.application.core.usecases.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.CreateSquadOutputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateSquadUseCaseTest {

    @Mock
    private CreateSquadOutputGateway createSquadOutputGateway;

    private CreateSquadUseCase createSquadUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createSquadUseCase = new CreateSquadUseCase(createSquadOutputGateway);
    }

    @Test
    void testCreateWhenSquadNotNullThenReturnCreatedEmployee() {
        var squad = Squad.builder()
                .id(UUID.randomUUID())
                .name("Squad 1")
                .description("Squad 1 description")
                .maxCapacity(10)
                .isPublic(true)
                .build();

        when(createSquadOutputGateway.create(squad)).thenReturn(Optional.of(squad));
        var createdSquad = createSquadUseCase.create(squad).get();
        assertEquals(squad, createdSquad);
        verify(createSquadOutputGateway).create(squad);
    }

    @Test
    void testCreateEmployeeExceptionHandling() {
        var squad = new Squad();
        when(createSquadOutputGateway.create(squad)).thenThrow(new RuntimeException("Failed to create squad"));
        assertThrows(RuntimeException.class, () -> createSquadUseCase.create(squad));
        verify(createSquadOutputGateway, times(1)).create(squad);
    }
}