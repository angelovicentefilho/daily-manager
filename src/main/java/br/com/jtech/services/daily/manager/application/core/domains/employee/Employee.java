package br.com.jtech.services.daily.manager.application.core.domains.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;

    public static Employee newEmployee(final String email) {
        return Employee.builder().email(email).build();
    }

    public static List<Employee> fromDocuments(List<EmployeeDocument> documents) {
        return documents.stream().map(Employee::fromDocument).toList();
    }

    public EmployeeDocument toDocument() {
        return EmployeeDocument.builder()
                .id(getId())
                .name(getName())
                .username(getUsername())
                .password(getPassword())
                .email(getEmail())
                .build();
    }

    public static Employee fromDocument(EmployeeDocument document) {
        var employee = new Employee();
        BeanUtils.copyProperties(document, employee);
        return employee;
    }

    public static Employee fromRequest(EmployeeRequest request) {
        var employee = new Employee();
        BeanUtils.copyProperties(request, employee);
        employee.setId(GenId.newUuid(request.getId()));
        return employee;
    }
}
