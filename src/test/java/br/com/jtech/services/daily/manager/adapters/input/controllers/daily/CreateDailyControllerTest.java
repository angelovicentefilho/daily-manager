package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.controllers.squad.CreateSquadController;
import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.input.daily.CreateDailyInputGateway;
import br.com.jtech.services.daily.manager.application.ports.input.squad.CreateSquadInputGateway;
import br.com.jtech.services.daily.manager.config.infra.mongodb.MongoTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateDailyController.class)
class CreateDailyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateDailyInputGateway createDailyInputGateway;

    private static final UUID ID = UUID.randomUUID();

    @Test
    public void testCreateDailyWhenValidRequestThenCreated() throws Exception {
        final String validPayload = "{\"squadId\":\"" + ID.toString() + "\",\"authorUsername\":\"filipe\",\"summary\":\"Daily 1 description\"}";
        final var request = DailyRequest.builder().squadId(ID.toString()).authorUsername("filipe").summary("Daily 1 description").build();
        final var daily = Daily.fromRequest(request);
        Mockito.when(createDailyInputGateway.create(Mockito.argThat(actualDaily ->
                        actualDaily.getSquad().getId().equals(daily.getSquad().getId()) &&
                        actualDaily.getSummary().equals(daily.getSummary()) &&
                        actualDaily.getAuthor().getUsername().equals(daily.getAuthor().getUsername())
        ))).thenReturn(Optional.of(daily));
        mockMvc.perform(post("/api/v1/dailies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPayload))
                .andExpect(status().isCreated());
        Mockito.verify(createDailyInputGateway, Mockito.times(1)).create(any(Daily.class));
    }

    @Test
    public void testCreateDailyWhenInvalidRequestThenBadRequest() throws Exception {
        final String invalidPayload = "{\"squadId\":\"" + ID.toString() + "\",\"summary\":\"Daily 1 description\"}";
        mockMvc.perform(post("/api/v1/dailies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest());
    }
}