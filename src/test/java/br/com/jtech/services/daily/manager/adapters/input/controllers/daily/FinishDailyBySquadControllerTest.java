package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.input.daily.FinishDailyBySquadInputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FinishDailyBySquadControllerTest {

    private FinishDailyBySquadController finishDailyBySquadController;

    @Mock
    private FinishDailyBySquadInputGateway finishDailyBySquadInputGateway;

    @BeforeEach
    public void setUp() {
        finishDailyBySquadController = new FinishDailyBySquadController(finishDailyBySquadInputGateway);
    }

    @Test
    public void testFinishBySquadWhenSquadExistsThenReturnOk() {
        // Arrange
        final String squad = "Test Squad";
        final var author = Employee.builder().id(GenId.newUuid()).name("Test Author").build();
        final var daily = Daily.builder().id(GenId.newUuid()).squad(Squad.builder().name(squad).id(GenId.newUuid()).build()).build();
        daily.setAuthor(author);
        when(finishDailyBySquadInputGateway.finish(squad)).thenReturn(Optional.of(daily));

        // Act
        ResponseEntity<DailyResponse> response = finishDailyBySquadController.finishBySquad(squad);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        verify(finishDailyBySquadInputGateway, times(1)).finish(squad);
    }

    @Test
    public void testFinishBySquadWhenSquadDoesNotExistThenReturnBadRequest() {
        // Arrange
        String squad = "Nonexistent Squad";
        when(finishDailyBySquadInputGateway.finish(squad)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<DailyResponse> response = finishDailyBySquadController.finishBySquad(squad);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNull();
        verify(finishDailyBySquadInputGateway, times(1)).finish(squad);
    }
}