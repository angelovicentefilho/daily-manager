package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.input.squad.UpdateSquadInputGateway;
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

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(UpdateSquadController.class)
public class UpdateSquadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UpdateSquadInputGateway updateSquadInputGateway;

    private static final UUID ID = UUID.randomUUID();

    @Test
    public void testUpdateSquadWhenValidRequestThenOk() throws Exception {
        SquadRequest request = SquadRequest.builder()
                .id(ID.toString())
                .name("Squad 1")
                .description("Squad 1 description")
                .build();

        when(updateSquadInputGateway.update(Mockito.any())).thenReturn(Optional.of(Squad.fromRequest(request)));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/squads/{id}", ID.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Squad 2\",\"description\":\"Squad 2 description\"}"))
                .andExpect(status().isOk());

        Mockito.verify(updateSquadInputGateway, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    public void testUpdateSquadWhenInvalidRequestThenBadRequest() throws Exception {
        String id = ID.toString();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/squads/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(updateSquadInputGateway);
    }
}