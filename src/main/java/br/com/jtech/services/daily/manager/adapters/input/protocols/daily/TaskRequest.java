package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequest implements Serializable {
    private String id;
    @NotEmpty(message = "Title task cannot be empty!")
    private String title;
    @NotEmpty(message = "Task description cannot be empty!")
    private String description;
    private String status;
    @NotEmpty(message = "Task priority cannot be empty!")
    private String priority;
    @NotNull(message = "Due date cannot be null!")
    private LocalDate dueDate;
    @NotNull(message = "Assigner cannot be null!")
    private EmployeeRequest assignee;
    private String notes;
}
