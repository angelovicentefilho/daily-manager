package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse.fromDomain;

@RequiredArgsConstructor
@JtechRestController(value = "/api/v1/dailies/employees")
public class FindEmployeeByEmailController {

    private final FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;

    @GetMapping("/{email}")
    public ResponseEntity<EmployeeResponse> findByEmail(@PathVariable String email) {
        return findEmployeeByEmailOutputGateway.findByEmail(email)
                .map(employee -> ResponseEntity.ok(fromDomain(employee)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
