package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse.fromDomain;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@JtechRestController(value = "/api/v1/dailies/employees")
public class FindEmployeeByUsernameController {

    private final FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;

    @GetMapping("/username/{username}")
    public ResponseEntity<EmployeeResponse> findByUsername(@PathVariable String username) {
        var employee = findEmployeeByUsernameOutputGateway.findByUsername(username);
        return ok(fromDomain(employee));
    }
}
