package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.application.ports.input.squad.DeleteSquadInputGateway;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(DeleteSquadController.class)
public class DeleteSquadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeleteSquadInputGateway deleteSquadInputGateway;

    @Test
    public void testDeleteByIdWhenValidIdThenNoContent() throws Exception {
        String id = "123";
        Mockito.doNothing().when(deleteSquadInputGateway).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/squads/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deleteSquadInputGateway, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeleteAllThenNoContent() throws Exception {
        Mockito.doNothing().when(deleteSquadInputGateway).deleteAll();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/squads/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deleteSquadInputGateway, Mockito.times(1)).deleteAll();
    }
}
