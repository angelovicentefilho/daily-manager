package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.mongodb.MongoTestConfig;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(FindSquadByIdController.class)
public class FindSquadByIdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @Test
    public void testFindSquadByIdWhenSquadsExistThenOk() throws Exception {
        var id = GenId.newUuid(GenId.newId());
        var squad = Squad.builder()
                .id(id)
                .name("Squad 1")
                .description("Squad 1 description")
                .members(List.of())
                .maxCapacity(10)
                .isPublic(true)
                .build();
        when(findSquadByIdOutputGateway.findById(id)).thenReturn(squad);
        mockMvc.perform(get("/api/v1/squads/{squadId}", id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(findSquadByIdOutputGateway, times(1)).findById(id);
    }
}
