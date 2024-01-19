package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FindEmployeeByUsernameController.class)
public class FindEmployeeByUsernameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;

    @Test
    public void testFindByUsernameWhenEmployeeExistsThenReturnEmployee() throws Exception {
        // Arrange
        String username = "johndoe";
        Optional<Employee> employee = Optional.of(new Employee(UUID.randomUUID(), "John Doe", username, "password123", "john.doe@example.com", null));
        when(findEmployeeByUsernameOutputGateway.findByUsername(username)).thenReturn(employee);

        // Act & Assert
        mockMvc.perform(get("/api/v1/dailies/employees/username/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(employee.get().getId())))
                .andExpect(jsonPath("$.name", is(employee.get().getName())))
                .andExpect(jsonPath("$.username", is(employee.get().getUsername())))
                .andExpect(jsonPath("$.email", is(employee.get().getEmail())));
    }

    @Test
    public void testFindByUsernameWhenEmployeeDoesNotExistThenReturnNotFound() throws Exception {
        String username = "unknown";
        when(findEmployeeByUsernameOutputGateway.findByUsername(username)).thenReturn(null);

        mockMvc.perform(get("/api/v1/dailies/employees/username/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindByUsernameWhenInvalidUsernameThenReturnBadRequest() throws Exception {
        String invalidUsername = "";

        mockMvc.perform(get("/api/v1/dailies/employees/username/{username}", invalidUsername)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
