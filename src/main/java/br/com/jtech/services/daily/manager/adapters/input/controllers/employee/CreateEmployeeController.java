package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.CreateEmployeeInputGateway;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse.fromDomain;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class CreateEmployeeController {

    private final CreateEmployeeInputGateway createEmployeeInputGateway;

    @PostMapping
    @Tag(name = "Create a new Employee", description = "Method to create an Employee")
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid EmployeeRequest request) {
        var domain = createEmployeeInputGateway.create(Employee.fromRequest(request));
        return ResponseEntity.status(CREATED).body(fromDomain(domain));
    }

}
