package br.com.jtech.services.daily.manager.adapters.input.protocols.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResponse implements Serializable {
    private String id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private EmployeeResponse assignee;
    private String notes;
}
