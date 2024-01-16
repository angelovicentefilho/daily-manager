package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.CreateEmployeeInputGateway;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Employee", description = "Manager user")
public class CreateEmployeeController {

    private final CreateEmployeeInputGateway createEmployeeInputGateway;

    @PostMapping
    @Operation(operationId = "employeeCreate", description = "Method to create new Employee")
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid EmployeeRequest request) {
        var domain = createEmployeeInputGateway.create(Employee.fromRequest(request));
        return ResponseEntity.status(CREATED).body(fromDomain(domain));
    }

}
