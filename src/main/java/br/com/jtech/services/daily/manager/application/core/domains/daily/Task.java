package br.com.jtech.services.daily.manager.application.core.domains.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.TaskRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.TaskDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private UUID id;
    private String title;
    private String description;
    private StatusTask status;
    private PriorityTask priority;
    private LocalDate dueDate;
    private Employee assignee;
    private String notes;

    public static List<Task> fromDocuments(List<TaskDocument> tasks) {
        return null;
    }

    public static List<Task> fromRequest(List<TaskRequest> tasks) {
        return null;
    }

    public static List<TaskDocument> toDocuments(List<Task> tasks) {
        return null;
    }
}
