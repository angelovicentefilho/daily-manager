package br.com.jtech.services.daily.manager.application.core.domains.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.TaskRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.TaskDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import lombok.*;
import org.springframework.beans.BeanUtils;

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

    public static Task fromRequest(TaskRequest request) {
        UUID requestId = null;
        if (request.getId() != null) {
            requestId = GenId.newUuid(request.getId());
        }
        Employee assignee = null;
        if (request.getAssignee() != null) {
            assignee = Employee.fromRequest(request.getAssignee());
        }
        return Task.builder()
                .id(requestId)
                .title(request.getTitle())
                .description(request.getDescription())
                .status(StatusTask.valueOf(request.getStatus()))
                .priority(PriorityTask.valueOf(request.getPriority()))
                .dueDate(request.getDueDate())
                .assignee(assignee)
                .notes(request.getNotes())
                .build();
    }

    public static List<Task> fromRequests(List<TaskRequest> requests) {
        return requests.stream().map(Task::fromRequest).toList();
    }

    public static List<TaskDocument> toDocuments(List<Task> tasks) {
        return null;
    }
}
