package br.com.jtech.services.daily.manager.adapters.input.protocols.employee;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResponse implements Serializable {
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;

    private List<EmployeeResponse> employees;

    public static EmployeeResponse fromDomain(Employee domain) {
        var response = new EmployeeResponse();
        BeanUtils.copyProperties(domain, response);
        if (domain.getId() != null) {
            response.setId(domain.getId().toString());
        }
        return response;
    }

    public static EmployeeResponse fromDomains(List<Employee> employees) {
        var list =  employees.stream().map(EmployeeResponse::fromDomain).toList();
        return EmployeeResponse.builder()
                .employees(list)
                .build();
    }
}
