package br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.BaseDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;
import br.com.jtech.services.daily.manager.config.infra.mongo.Cascade;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias(value = "Task")
@Document(collection = "tasks")
@EqualsAndHashCode(callSuper = true)
public class TaskDocument extends BaseDocument implements Serializable {

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    @DBRef
    @Cascade
    private EmployeeDocument assignee;
    private String notes;

    public static List<TaskDocument> fromDomains(List<Task> tasks) {
        return null;
    }
}
