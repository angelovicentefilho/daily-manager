package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(FindEmployeeByEmailController.class)
public class FindEmployeeByEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;

    @Test
    public void testFindEmployeeByEmailWhenEmployeeExistsThenOk() throws Exception {
        String email = "johndoe@example.com";
        Employee employee = Employee.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .username("johndoe")
                .email(email)
                .build();

        Mockito.when(findEmployeeByEmailOutputGateway.findByEmail(email)).thenReturn(Optional.of(employee));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/dailies/employees/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(findEmployeeByEmailOutputGateway, Mockito.times(1)).findByEmail(email);
    }

    @Test
    public void testFindEmployeeByEmailWhenEmployeeDoesNotExistThenNotFound() throws Exception {
        String email = "johndoe@example.com";

        Mockito.when(findEmployeeByEmailOutputGateway.findByEmail(email)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/dailies/employees/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Mockito.verify(findEmployeeByEmailOutputGateway, Mockito.times(1)).findByEmail(email);
    }
}