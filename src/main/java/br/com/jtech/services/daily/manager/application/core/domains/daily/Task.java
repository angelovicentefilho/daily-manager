package br.com.jtech.services.daily.manager.application.core.domains.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.TaskRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.TaskDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
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
        return tasks.stream().map(Task::fromDocument).toList();
    }

    public static Task fromRequest(TaskRequest request) {
        var task = new Task();
        BeanUtils.copyProperties(request, task);
        task.setPriority(PriorityTask.of(request.getPriority()));
        task.setStatus(StatusTask.of(request.getStatus()));
        return task;
    }

    public static Task fromDocument(TaskDocument document) {
        var task = new Task();
        BeanUtils.copyProperties(document, task);
        task.setPriority(PriorityTask.of(document.getPriority()));
        task.setStatus(StatusTask.of(document.getStatus()));
        task.setAssignee(Employee.fromDocument(document.getAssignee()));
        return task;
    }

    public static List<Task> fromRequests(List<TaskRequest> tasks) {
        return tasks.stream().map(Task::fromRequest).toList();
    }

    public TaskDocument toDocument() {
        return TaskDocument.builder()
                .id(getId())
                .assignee(getAssignee().toDocument())
                .description(getDescription())
                .dueDate(getDueDate())
                .notes(getNotes())
                .title(getTitle())
                .status(getStatus().name())
                .priority(getPriority().name())
                .build();
    }

    public TaskDocument toDocument(Task task) {
        return TaskDocument.builder()
                .id(task.getId())
                .assignee(task.getAssignee().toDocument())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .notes(task.getNotes())
                .title(task.getTitle())
                .status(task.getStatus().name())
                .priority(task.getPriority().name())
                .build();
    }

    public static List<TaskDocument> toDocuments(List<Task> tasks) {
        return tasks.stream().map(Task::toDocument).toList();
    }
}
