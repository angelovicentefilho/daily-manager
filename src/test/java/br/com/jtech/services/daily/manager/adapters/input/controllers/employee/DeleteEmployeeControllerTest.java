package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.application.ports.input.employee.DeleteEmployeeInputGateway;
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
@WebMvcTest(DeleteEmployeeController.class)
public class DeleteEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeleteEmployeeInputGateway deleteEmployeeInputGateway;

    @Test
    public void testDeleteByEmailWhenValidEmailThenNoContent() throws Exception {
        String email = "test@example.com";
        Mockito.doNothing().when(deleteEmployeeInputGateway).deleteByEmail(email);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/dailies/email/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deleteEmployeeInputGateway, Mockito.times(1)).deleteByEmail(email);
    }

    @Test
    public void testDeleteByIdWhenValidIdThenNoContent() throws Exception {
        String id = "123";
        Mockito.doNothing().when(deleteEmployeeInputGateway).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/dailies/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deleteEmployeeInputGateway, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByUsernameWhenValidUsernameThenNoContent() throws Exception {
        String username = "john_doe";
        Mockito.doNothing().when(deleteEmployeeInputGateway).deleteByUsername(username);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/dailies/user/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deleteEmployeeInputGateway, Mockito.times(1)).deleteByUsername(username);
    }

    @Test
    public void testDeleteAllThenNoContent() throws Exception {
        Mockito.doNothing().when(deleteEmployeeInputGateway).deleteAll();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/dailies/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deleteEmployeeInputGateway, Mockito.times(1)).deleteAll();
    }
}
