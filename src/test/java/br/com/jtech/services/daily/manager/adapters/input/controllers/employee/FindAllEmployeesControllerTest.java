package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindAllEmployeesOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.mongodb.MongoTestConfig;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(FindAllEmployeesController.class)
public class FindAllEmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindAllEmployeesOutputGateway findAllEmployeesOutputGateway;

    @Test
    public void testFindAllEmployeesWhenEmployeesExistThenOk() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder()
                .id(GenId.newUuid(GenId.newId()))
                .name("John Doe")
                .username("johndoe")
                .email("johndoe@example.com")
                .build());

        Mockito.when(findAllEmployeesOutputGateway.findAll()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/dailies/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(findAllEmployeesOutputGateway, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindAllEmployeesWhenNoEmployeesExistThenOk() throws Exception {
        Mockito.when(findAllEmployeesOutputGateway.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/dailies/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(findAllEmployeesOutputGateway, Mockito.times(1)).findAll();
    }
}