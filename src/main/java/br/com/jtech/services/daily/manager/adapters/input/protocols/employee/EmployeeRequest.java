package br.com.jtech.services.daily.manager.adapters.input.protocols.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRequest implements Serializable {
    private String id;
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    @NotBlank(message = "Username cannot be empty!")
    private String username;
    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 5, max = 20, message = "Password min 5 and max 20 characters!")
    private String password;
    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Email is not valid!")
    private String email;

    private List<EmployeeRequest> employees;
}
