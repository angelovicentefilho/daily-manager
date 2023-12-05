package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private EmployeeRequest assignee;
    private String notes;
}
