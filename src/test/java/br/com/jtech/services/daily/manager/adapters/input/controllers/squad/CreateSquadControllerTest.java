package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateSquadController.class)
class CreateSquadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateSquadInputGateway createSquadInputGateway;

    private static final UUID ID = UUID.randomUUID();

    @Test
    public void testCreateSquadWhenValidRequestThenCreated() throws Exception {
        String validPayload = "{\"name\":\"Squad 1\",\"description\":\"Squad 1 description\",\"members\":[],\"maxCapacity\":10,\"isPublic\":true}";
        var request = SquadRequest.builder()
                .id(ID.toString())
                .name("Squad 1")
                .description("Squad 1 description")
                .members(List.of())
                .maxCapacity(10)
                .isPublic(true)
                .build();
        var squad = Squad.fromRequest(request);
        Mockito.when(createSquadInputGateway.create(Mockito.argThat(actualSquad ->
                        actualSquad.getName().equals(squad.getName()) &&
                        actualSquad.getDescription().equals(squad.getDescription()) &&
                        actualSquad.getMaxCapacity().equals(squad.getMaxCapacity()) &&
                        actualSquad.getIsPublic().equals(squad.getIsPublic()) &&
                        actualSquad.getMembers().equals(squad.getMembers())
        ))).thenReturn(squad);
        mockMvc.perform(post("/api/v1/squads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPayload))
                .andExpect(status().isCreated());
        Mockito.verify(createSquadInputGateway, Mockito.times(1)).create(any(Squad.class));
    }

    @Test
    public void testCreateSquadWhenInvalidRequestThenBadRequest() throws Exception {
        String invalidPayload = "{\"description\":\"Squad 1 description\",\"members\":[],\"maxCapacity\":10,\"isPublic\":true}";
        mockMvc.perform(post("/api/v1/squads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest());
    }
}