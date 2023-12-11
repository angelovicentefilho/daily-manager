package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.ports.input.employee.CreateEmployeeInputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.Https;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dailies")
@RequiredArgsConstructor
public class CreateEmployeeController {

    private final CreateEmployeeInputGateway createEmployeeInputGateway;

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid EmployeeRequest request) {
        return createEmployeeInputGateway.create(Employee.fromRequest(request))
                .map(EmployeeResponse::fromDomain)
                .map(Https::CREATED)
                .orElse(ResponseEntity.badRequest().build());
    }

}
