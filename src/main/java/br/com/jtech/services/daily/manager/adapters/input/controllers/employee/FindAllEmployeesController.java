package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindAllEmployeesOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse.fromDomains;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@JtechRestController(value = "/api/v1/employees")
public class FindAllEmployeesController {

    private final FindAllEmployeesOutputGateway findAllEmployeesOutputGateway;

    @GetMapping
    public ResponseEntity<EmployeeResponse> findAll() {
        var employees = findAllEmployeesOutputGateway.findAll();
        return ok(fromDomains(employees));
    }

}
