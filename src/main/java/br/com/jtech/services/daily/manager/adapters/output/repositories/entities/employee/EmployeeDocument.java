package br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.BaseDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias(value = "Employee")
@Document(collection = "employees")
@EqualsAndHashCode(callSuper = true)
public class EmployeeDocument extends BaseDocument implements Serializable {
    private String name;
    private String username;
    private String password;
    private String email;

    public static EmployeeDocument fromDomain(Employee author) {
        return null;
    }
}
