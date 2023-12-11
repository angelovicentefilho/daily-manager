package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.CreateEmployeeInputGateway;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateEmployeeController.class)
public class CreateEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateEmployeeInputGateway createEmployeeInputGateway;

    private static final UUID ID = UUID.randomUUID();

    @Test
    public void testCreateEmployeeWhenValidRequestThenCreated() throws Exception {
        EmployeeRequest request = EmployeeRequest.builder()
                .id(ID.toString())
                .name("John Doe")
                .username("johndoe")
                .password("password")
                .email("johndoe@example.com")
                .build();

        Employee employee = Employee.fromRequest(request);
        EmployeeResponse response = EmployeeResponse.fromDomain(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/dailies/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"password\":\"password\",\"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testCreateEmployeeWhenInvalidRequestThenBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/dailies/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"password\":\"\",\"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(createEmployeeInputGateway);
    }
}