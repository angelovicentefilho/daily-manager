package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.ports.input.employee.UpdateEmployeeInputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse.fromDomain;
import static br.com.jtech.services.daily.manager.application.core.domains.employee.Employee.fromRequest;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@JtechRestController(value = "/api/v1/employees")
public class UpdateEmployeeController {

    private final UpdateEmployeeInputGateway updateEmployeeInputGateway;

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable String id, @RequestBody @Valid EmployeeRequest request) {
        request.setId(id);
        var employee = updateEmployeeInputGateway.update(fromRequest(request));
        return ok(fromDomain(employee));
    }

}
