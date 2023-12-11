package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.UpdateEmployeeInputGateway;
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
@WebMvcTest(UpdateEmployeeController.class)
public class UpdateEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UpdateEmployeeInputGateway updateEmployeeInputGateway;

    private static final UUID ID = UUID.randomUUID();

    @Test
    public void testUpdateEmployeeWhenValidRequestThenOk() throws Exception {
        EmployeeRequest request = EmployeeRequest.builder()
                .id(ID.toString())
                .name("John Doe")
                .username("johndoe")
                .password("password")
                .email("johndoe@example.com")
                .build();

        EmployeeResponse response = EmployeeResponse.builder()
                .id(ID.toString())
                .name("John Doe")
                .username("johndoe")
                .email("johndoe@example.com")
                .build();

        Mockito.when(updateEmployeeInputGateway.update(Mockito.any())).thenReturn(Optional.of(Employee.fromRequest(request)));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/dailies/employees/{id}", ID.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"password\":\"password\",\"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isOk());

        Mockito.verify(updateEmployeeInputGateway, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    public void testUpdateEmployeeWhenInvalidRequestThenBadRequest() throws Exception {
        String id = ID.toString();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/dailies/employees/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"password\":\"\",\"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(updateEmployeeInputGateway);
    }
}