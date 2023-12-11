package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.ports.input.employee.UpdateEmployeeInputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import br.com.jtech.services.daily.manager.config.infra.utils.Https;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static br.com.jtech.services.daily.manager.application.core.domains.employee.Employee.fromRequest;
import static org.springframework.http.ResponseEntity.notFound;

@RequiredArgsConstructor
@JtechRestController(value = "/api/v1/dailies/employees")
public class UpdateEmployeeController {

    private final UpdateEmployeeInputGateway updateEmployeeInputGateway;

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable String id, @RequestBody @Valid EmployeeRequest request) {
        request.setId(id);
        return updateEmployeeInputGateway.update(fromRequest(request))
                .map(EmployeeResponse::fromDomain)
                .map(Https::OK)
                .orElseGet(() -> notFound().build());
    }

}
